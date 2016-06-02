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
public class Linechart extends ApplicationFrame {

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
    public Linechart(final String title, Simulator sim) throws SimulationException {
        super(title);
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
	private TimeSeriesCollection createTimeSeriesData() throws SimulationException {
		TimeSeriesCollection tsc = new TimeSeriesCollection();
		TimeSeries bookTotal = new TimeSeries("Total Bookings");
		TimeSeries firstTotal = new TimeSeries("First");
		TimeSeries busTotal = new TimeSeries("Business");
		TimeSeries premTotal = new TimeSeries("Premium");
		TimeSeries econTotal = new TimeSeries("Economy");
		TimeSeries freeTotal = new TimeSeries("Free");

		//Base time, data set up - the calendar is needed for the time points
		Calendar cal = GregorianCalendar.getInstance(); 

		int first = 0;
		int business = 0;
		int premium = 0;
		int economy = 0;
		int free = 0;

		for(int i=21; i<=18*7; i++){
			cal.set(2016,0,i,6,0);
			Date timePoint = cal.getTime();
			Bookings book = sim.getFlightStatus(i);
			first = book.getNumFirst();
			business = book.getNumBusiness();
			premium = book.getNumPremium();
			economy = book.getNumEconomy();
			free = book.getAvailable();

	        //This is important - steal it shamelessly - Shamelessly stolen
			firstTotal.add(new Day(timePoint),first);
			busTotal.add(new Day(timePoint),business);
			premTotal.add(new Day(timePoint),premium);
			econTotal.add(new Day(timePoint),economy);
			bookTotal.add(new Day(timePoint),economy+business+premium+first);
			freeTotal.add(new Day(timePoint), free);
		}

		//Collection
		tsc.addSeries(freeTotal);
		tsc.addSeries(bookTotal);
		tsc.addSeries(econTotal);
		tsc.addSeries(premTotal);
		tsc.addSeries(busTotal);
		tsc.addSeries(firstTotal);
		return tsc;
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
    
}
