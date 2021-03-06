/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.SwingUtilities;

import org.jfree.ui.RefineryUtilities;

import asgn2Aircraft.AircraftException;
import asgn2Passengers.PassengerException;

/**
 * Class to operate the simulation, taking parameters and utility methods from the Simulator
 * to control the available resources, and using Log to provide a record of operation. 
 * 
 * @author Mitchell and Nathan
 *
 */ 
public class SimulationRunner {
	static GUISimulator GUI;
	
	
	/**
	 * Main program for the simulation 
	 * 
	 * @param args Arguments to the simulation - 
	 * see {@link asgn2Simulators.SimulationRunner#printErrorAndExit()}
	 */
	
	
	public static void main(String[] args) {
		final int NUM_ARGS = 9; 
		Simulator s = null; 
		Log l = null; 
		try {
			switch (args.length) {
				case 0: {
					System.out.println("case 0");
					runGuiDefault();
					//s = new Simulator(); 
					break;
				}
				case 1: {
					System.out.println("case 1");
					if (args[0]=="true"){
						//Run gui with defaults
						runGuiDefault();
					}else if(args[0]=="false"){
						//Run as normal
						s = new Simulator(); 
						break;					
					}else{
						printErrorAndExit();
					}
				}
				case NUM_ARGS: {
					System.out.println("case 9");
					s = createSimulatorUsingArgs(args);
					l = new Log();
					//Run the simulation 
					SimulationRunner sr = new SimulationRunner(s,l);			
					try {
						sr.runSimulation();
					} catch (Exception e) {
						e.printStackTrace();
						System.exit(-1);
					} 
					break;
				}
				case 10: {
					System.out.println("case 10");
					String[] simulatorParameters = new String[NUM_ARGS];
					System.arraycopy(args, 1, simulatorParameters, 0, NUM_ARGS);
					if (args[0]=="true"){
						//Run with custom values
						runGuiCustom(simulatorParameters);
					}else if(args[0]=="false"){
						//Run as normal
						s = new Simulator(); 
						break;
						
					}else{
						printErrorAndExit();
					}
					s = createSimulatorUsingArgs(args); 
					break;
				}
				default: {
					printErrorAndExit(); 
				}
			}

			l = new Log();
		} catch (SimulationException | IOException e1) {
			e1.printStackTrace();
			System.exit(-1);
		}
	
		
	}
	
	
	
	
	/**
	 * Helper to process args for Simulator  
	 * 
	 * @param args Command line arguments (see usage message) 
	 * @return new <code>Simulator</code> from the arguments 
	 * @throws SimulationException if invalid arguments. 
	 * See {@link asgn2Simulators.Simulator#Simulator(int, int, double, double, double, double, double, double, double)}
	 */
	private static Simulator createSimulatorUsingArgs(String[] args) throws SimulationException {
		int seed = Integer.parseInt(args[0]);
		int maxQueueSize = Integer.parseInt(args[1]);
		double meanBookings = Double.parseDouble(args[2]);
		double sdBookings = Double.parseDouble(args[3]);
		double firstProb = Double.parseDouble(args[4]);
		double businessProb = Double.parseDouble(args[5]);
		double premiumProb = Double.parseDouble(args[6]);
		double economyProb = Double.parseDouble(args[7]);
		double cancelProb = Double.parseDouble(args[8]);
		return new Simulator(seed,maxQueueSize,meanBookings,sdBookings,firstProb,businessProb,
						  premiumProb,economyProb,cancelProb);
		
	}
	
	/**
	 *  Helper to generate usage message 
	 */
	private static void printErrorAndExit() {
		String str = "Usage: java asgn2Simulators.SimulationRunner [SIM Args]\n";
		str += "SIM Args: seed maxQueueSize meanBookings sdBookings "; 
		str += "firstProb businessProb premiumProb economyProb cancelProb\n";
		str += "If no arguments, default values are used\n";
		System.err.println(str);
		System.exit(-1);
	}
	
	
	private Simulator sim;
	private Log log; 

	/**
	 * Constructor just does initialisation 
	 * 
	 * @param sim <code>Simulator</code> containing simulation parameters
	 * @param log <code>Log</code> object supporting record of operation 
	 */
	public SimulationRunner(Simulator sim, Log log) {
		this.sim = sim;
		this.log = log;
	}

	/**
	 * Method to run the simulation from start to finish. 
	 * Exceptions are propagated upwards as necessary 
	 * 
	 * @throws AircraftException See methods from {@link asgn2Simulators.Simulator} 
	 * @throws PassengerException See methods from {@link asgn2Simulators.Simulator} 
	 * @throws SimulationException See methods from {@link asgn2Simulators.Simulator} 
	 * @throws IOException on logging failures See methods from {@link asgn2Simulators.Log} 

	 */
	public void runSimulation() throws AircraftException, PassengerException, SimulationException, IOException {
		this.sim.createSchedule();
		String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		GUI.addTextTooScreen(timeLog + ": Start of Simulation\n");
		GUI.addTextTooScreen(sim.toString() + "\n");
		String capacities = sim.getFlights(Constants.FIRST_FLIGHT).initialState();
		GUI.addTextTooScreen(capacities);
		
		
		this.log.initialEntry(this.sim);
		//Main simulation loop 
		for (int time=0; time<=Constants.DURATION; time++) {
			this.sim.resetStatus(time); 
			this.sim.rebookCancelledPassengers(time); 
			this.sim.generateAndHandleBookings(time);
			this.sim.processNewCancellations(time);
			if (time >= Constants.FIRST_FLIGHT) {
				this.sim.processUpgrades(time);
				this.sim.processQueue(time);
				this.sim.flyPassengers(time);
				this.sim.updateTotalCounts(time); 
				this.log.logFlightEntries(time, sim);
			} else {
				this.sim.processQueue(time);
			}
			//Log progresss 
			GUI.mySim = sim;
			this.log.logQREntries(time, sim);
			boolean flying = (time >= Constants.FIRST_FLIGHT);
			GUI.addTextTooScreen(sim.getSummary(time, flying));
			this.log.logEntry(time,this.sim);
		}
		Linechart dummytest = new Linechart("Random Data", this.sim);
		dummytest.pack();
        RefineryUtilities.centerFrameOnScreen(dummytest);
        dummytest.setVisible(true);
		Barchart dummytest1 = new Barchart("Random Data", this.sim);
		dummytest1.pack();
        RefineryUtilities.centerFrameOnScreen(dummytest1);
        dummytest1.setVisible(true);
		this.sim.finaliseQueuedAndCancelledPassengers(Constants.DURATION); 
		GUI.addTextTooScreen(sim.getStatus(Constants.DURATION));
		this.log.logQREntries(Constants.DURATION, sim);
		timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		GUI.addTextTooScreen("\n" + timeLog + ": End of Simulation\n");
		GUI.addTextTooScreen(sim.finalState());
		this.log.finalise(this.sim);
	}
	
	private static void runGuiDefault(){
		String[] emptyString = {};
		GUI = new GUISimulator("BorderLayout", emptyString);
		SwingUtilities.invokeLater(GUI);
	}
	
	private static void runGuiCustom(String[] args){
		GUI = new GUISimulator("BorderLayout", args);
		SwingUtilities.invokeLater(GUI);
	}
}
