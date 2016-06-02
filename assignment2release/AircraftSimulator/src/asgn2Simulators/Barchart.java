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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
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
public class Barchart extends ApplicationFrame {

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
    public Barchart(final String title, Simulator sim) throws SimulationException {
        super(title);
        this.sim = sim;
        final DefaultCategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createBarChart("Bar Chart", "X axis", "Y axis", dataset);
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
	private DefaultCategoryDataset createDataset() throws SimulationException {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		


		int queue = 0;
		int capacity = 0;

		for(int i=Constants.FIRST_FLIGHT+1; i<Constants.DURATION; i++){
			if(capacity < (sim.getFlights(i).getCurrentCounts().getTotal() + sim.getFlights(i).getCurrentCounts().getAvailable())){
				capacity = (sim.getFlights(i).getCurrentCounts().getTotal() + sim.getFlights(i).getCurrentCounts().getAvailable());
			}
		}
		for(int q : sim.queues){
			if(queue < q){
				queue = q;
			}
		}
		dataset.setValue(capacity, "Number", "Capacity");
		dataset.setValue(queue, "Number", "Queued");
		dataset.setValue(sim.numRefused(), "Number", "Refused");

		//Collection
		return dataset;
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
