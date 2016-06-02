/**
 * 
 * This file is part of the AircraftSimulator Project, written as 
 * part of the assessment for CAB302, semester 1, 2016. 
 * 
 */
package asgn2Simulators;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.sun.corba.se.impl.orbutil.graph.Graph;


//import guiExploration.FramesAndPanels;
 



/**
 * @author hogan
 * @updated by Mitchell
 */
@SuppressWarnings("serial")




public class GUISimulator extends JFrame implements ActionListener, Runnable {

	
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	//Define panels
	private JPanel pnlOne;
	private JPanel pnlInputs;
	private JPanel pnlBtn;
	
	//Define Buttons
	private JButton btnRunSimulation;
	private JButton btnChangeChart;
	
	//Define Text inputs
	private JTextField textRNGSEED;
	private JTextField textDailyMean;
	private JTextField textQueueSize;
	private JTextField textCancellation;
	private JTextField textFirst;
	private JTextField textBusiness;
	private JTextField textPremium;
	private JTextField textEconomy;
	
	//Define Labels
	private JLabel labelRNGSEED;
	private JLabel labelDailyMean;
	private JLabel labelQueueSize;
	private JLabel labelCancellation;
	private JLabel labelFirst;
	private JLabel labelBusiness;
	private JLabel labelPremium;
	private JLabel labelEconomy;
	
	//Define Graphs
	Graph graph1;
	Graph graph2;
	
	//Define Text Area for output log
	private JTextArea textAreaLog;
	
	
	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public GUISimulator(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		createGUI(); 
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
        SwingUtilities.invokeLater(new GUISimulator("BorderLayout"));
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Get event source 
		Object src=e.getSource(); 
				 
		//Based on the source do an action 
		if (src== btnRunSimulation) {
			String[] test = {};
	        SimulationRunner.main(test);
		}
	}
	
	
	

	
	private void createGUI() { setSize(WIDTH, HEIGHT); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setLayout(new BorderLayout());
	
	setSize(WIDTH, HEIGHT);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    
    //Create various panels
    pnlOne = createPanel(Color.BLUE);
    pnlBtn = createPanel(Color.GRAY);
    pnlInputs = createPanel(Color.GREEN);

    
    //create various buttons
    btnRunSimulation = createButton("Run Simulation");
    btnChangeChart = createButton("Change Chart");
    
    
    //Create Various Labels
    labelRNGSEED = createLabel("RNGSEED");
	labelDailyMean = createLabel("DailyMean");
	labelQueueSize= createLabel("QueueSize");
	labelCancellation= createLabel("Cancellation");
	labelFirst= createLabel("First");
	labelBusiness= createLabel("Business");
	labelPremium= createLabel("Premium");
	labelEconomy= createLabel("Economy");
	
	
	//Create Various Text fields
    textRNGSEED = createTextField();
    textDailyMean = createTextField();
    textQueueSize= createTextField();
    textCancellation= createTextField();
    textFirst= createTextField();
    textBusiness= createTextField();
    textPremium= createTextField();
    textEconomy= createTextField();
    
    //create graphs
    //graph1 = new Graph();
    //graph2 = new Graph();
    
    //create Text Area
     textAreaLog =
	
	
    
    
    //draw panels
    this.getContentPane().add(pnlOne,BorderLayout.CENTER);
    this.getContentPane().add(pnlBtn,BorderLayout.SOUTH);
    this.getContentPane().add(pnlInputs,BorderLayout.NORTH);
    //pnlInputs.add(pnlLeftInputs,BorderLayout.WEST);
    //pnlInputs.add(pnlRightInputs,BorderLayout.SOUTH);

    //draw buttons to screen
    layoutButtonPanel();
    
    //Draw labels and text input to screen
    layoutInputsPanel();
    
    //set GUI as visible
    repaint(); 
    this.setVisible(true);

	}
	
	
	
	//********************************************************************
	// used to create components for the GUI, from week 8 lab
	//******************************************************************
	
	//creates text areas
	private JTextField createTextField() {
		JTextField jta = new JTextField("enter amount,1"); 
		jta.setFont(new Font("Arial",Font.PLAIN,12));
		//jta.setBorder(BorderFactory.createEtchedBorder());
		jta.setSize(350,100);
		return jta;
	}
	
	//used to create panels
	private JPanel createPanel(Color c) {
		JPanel jp = new JPanel();
		
		jp.setBackground(c);
		return jp;
	}
	
	//creates a button
	private JButton createButton(String str) {
		JButton jb = new JButton(str); 
		jb.addActionListener(this);
		return jb; 
	}
	
	//create a text area
	private JTextArea createTextArea() {
		JTextArea jta = new JTextArea(); 
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setFont(new Font("Arial",Font.BOLD,12));
		jta.setBorder(BorderFactory.createEtchedBorder());
		return jta;
	}
   
	
  //creates a label
  	private JLabel createLabel(String str) {
    	JLabel newLabel = new JLabel("",JLabel.CENTER);   
    	newLabel.setSize(350,100);
    	newLabel.setText(str);
  		return newLabel; 
  	}
	
	
	
	/**
     * 
     * A convenience method to add a component to given grid bag
     * layout locations. Code due to Cay Horstmann 
     *
     * @param c the component to add
     * @param constraints the grid bag constraints to use
     * @param x the x grid position
     * @param y the y grid position
     * @param w the grid width
     * @param h the grid height
     */
   private void addToPanel(JPanel jp,Component c, GridBagConstraints constraints, int x, int y, int w, int h) {  
      constraints.gridx = x;
      constraints.gridy = y;
      constraints.gridwidth = w;
      constraints.gridheight = h;
      jp.add(c, constraints);
   }
   
   
   
	
   private void layoutButtonPanel() {
		GridBagLayout layout = new GridBagLayout();
	    pnlBtn.setLayout(layout);
	    
	    //add components to grid
	    GridBagConstraints constraints = new GridBagConstraints(); 
	    
	    //Defaults
	    constraints.fill = GridBagConstraints.NONE;
	    constraints.anchor = GridBagConstraints.CENTER;
	    constraints.weightx = 100;
	    constraints.weighty = 100;
	    
	    addToPanel(pnlBtn, btnRunSimulation,constraints,0,0,2,1); 
	    addToPanel(pnlBtn, btnChangeChart,constraints,3,0,2,1); 

	}
   
   
	private void layoutInputsPanel() {
		GridBagLayout layout = new GridBagLayout();
	    pnlInputs.setLayout(layout);
	    
	    //add components to grid
	    GridBagConstraints constraints = new GridBagConstraints(); 
	    
	    //Defaults
	    constraints.fill = GridBagConstraints.VERTICAL;
	    constraints.anchor = GridBagConstraints.WEST;
	    constraints.weightx = 100;
	    constraints.weighty = 100;
	    
	    
	    //add labels
	    addToPanel(pnlInputs, labelRNGSEED,constraints,0,0,2,1); 
	    addToPanel(pnlInputs, labelDailyMean,constraints,8,0,2,1);
	    addToPanel(pnlInputs, labelQueueSize,constraints,0,2,2,1); 
	    addToPanel(pnlInputs, labelCancellation,constraints,8,2,2,1); 
	    addToPanel(pnlInputs, labelFirst,constraints,0,4,2,1); 
	    addToPanel(pnlInputs, labelBusiness,constraints,8,4,2,1); 
	    addToPanel(pnlInputs, labelPremium,constraints,0,6,2,1); 
	    addToPanel(pnlInputs, labelEconomy,constraints,8,6,2,1); 
	    
	    
	    //add text fields
	    addToPanel(pnlInputs, textRNGSEED,constraints,6,0,2,1); 
	    addToPanel(pnlInputs, textDailyMean,constraints,10,0,2,1);
	    addToPanel(pnlInputs, textQueueSize,constraints,6,2,2,1); 
	    addToPanel(pnlInputs, textCancellation,constraints,10,2,2,1); 
	    addToPanel(pnlInputs, textFirst,constraints,6,4,2,1); 
	    addToPanel(pnlInputs, textBusiness,constraints,10,4,2,1); 
	    addToPanel(pnlInputs, textPremium,constraints,6,6,2,1); 
	    addToPanel(pnlInputs, textEconomy,constraints,10,6,2,1); 
	    
	    
	    

	    
	    
	    
	    
	}
   
   
   
	
}
