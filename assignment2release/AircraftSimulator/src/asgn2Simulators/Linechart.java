/**
 *
 * This file is part of the AircraftSimulator Project, written as
 * part of the assessment for CAB302, semester 1, 2016.
 *
 */
package asgn2Simulators;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import asgn2Aircraft.AircraftException;
import asgn2Aircraft.Bookings;
import asgn2Passengers.PassengerException;

/**
 * Example code based on the Stack Overflow example and the
 * standard JFreeChart demos showing the construction of a time series
 * data set. Some of the data creation code is clearly a quick hack
 * and should not be taken as indicative of the required coding style.
 * @see http://stackoverflow.com/questions/5048852
 *
 *  */
@SuppressWarnings("serial")
public class Linechart extends JPanel {

    private static final String TITLE = "Random Bookings";
    private Simulator sim;

    /**
     * Constructor shares the work with the run method.
     * @param title Frame display title
     * @throws IOException 
     * @throws SimulationException 
     * @throws PassengerException 
     * @throws AircraftException 
     */
    public Linechart(final String title, Simulator sim) throws AircraftException, PassengerException, SimulationException, IOException {
        super();
        this.sim = sim;
        final TimeSeriesCollection dataset = createTimeSeriesData();
        JFreeChart chart = createChart(dataset);
        this.add(new ChartPanel(chart), BorderLayout.CENTER);
        JPanel btnPanel = new JPanel(new FlowLayout());
        this.add(btnPanel, BorderLayout.SOUTH);
    }

    /**
     * Private method creates the dataset. Lots of hack code in the
     * middle, but you should use the labelled code below
	 * @return collection of time series for the plot
     * @throws IOException 
     * @throws SimulationException 
     * @throws PassengerException 
     * @throws AircraftException 
	 */
	private TimeSeriesCollection createTimeSeriesData() throws AircraftException, PassengerException, SimulationException, IOException {
		TimeSeriesCollection tsc = new TimeSeriesCollection();
		TimeSeries bookTotal = new TimeSeries("Total Bookings");
		TimeSeries firstTotal = new TimeSeries("First");
		TimeSeries busTotal = new TimeSeries("Business");
		TimeSeries premTotal = new TimeSeries("Premium");
		TimeSeries econTotal = new TimeSeries("Economy");

		//Base time, data set up - the calendar is needed for the time points
		Calendar cal = GregorianCalendar.getInstance();
		Random rng = new Random(250); 

		int first = 0;
		int business = 0;
		int premium = 0;
		int economy = 0;

		for(int i=21; i<=18*7; i++){
			cal.set(2016,0,i,6,0);
			Date timePoint = cal.getTime();
			Bookings book = sim.getFlightStatus(i);
			first = book.getNumFirst();
			business = book.getNumBusiness();
			premium = book.getNumPremium();
			economy = book.getNumEconomy();

	        //This is important - steal it shamelessly - Shamelessly stolen
			firstTotal.add(new Day(timePoint),first);
			busTotal.add(new Day(timePoint),business);
			premTotal.add(new Day(timePoint),premium);
			econTotal.add(new Day(timePoint),economy);
			bookTotal.add(new Day(timePoint),economy+business+premium+first);
		}

		//Collection
		tsc.addSeries(bookTotal);
		tsc.addSeries(econTotal);
		tsc.addSeries(premTotal);
		tsc.addSeries(busTotal);
		tsc.addSeries(firstTotal);
		return tsc;
	}

	/**
	 * Utility method to implement a <a href="http://en.wikipedia.org/wiki/Bernoulli_trial">Bernoulli Trial</a>,
	 * a coin toss with two outcomes: success (probability successProb) and failure (probability 1-successProb)
	 * @param successProb double holding the success probability
	 * @param rng Random object
	 * @return true if trial was successful, false otherwise
	 */
	private boolean randomSuccess(double successProb,Random rng) {
		boolean result = rng.nextDouble() <= successProb;
		return result;
	}

    /**
     * Helper method to deliver the Chart - currently uses default colours and auto range
     * @param dataset TimeSeriesCollection for plotting
     * @returns chart to be added to panel
     */
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
            TITLE, "Days", "Passengers", dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setAutoRange(true);
        return result;
    }
    
    public static void run() throws AircraftException, PassengerException, SimulationException, IOException {
        Linechart demo = new Linechart("Random Bookings", null);
    }

    /**
     * Simple main GUI runner
     * @param args ignored
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException, AircraftException, PassengerException, SimulationException{
        run();
    }
}
