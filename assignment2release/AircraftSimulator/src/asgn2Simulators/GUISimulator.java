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
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


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
	public Simulator mySim;
	String[] inputArgs;
	
	//Define panels
	private JPanel pnlOne;
	private JPanel pnlInputs;
	private JPanel pnlBtn;
	
	//Define Buttons
	private JButton btnRunSimulation;
	private JButton btnChangeChart;
	private JButton btnSetDefault;
	
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
	public GUISimulator(String arg0, String[] args) throws HeadlessException {
		super(arg0);
		System.out.println("test");
		JFrame.setDefaultLookAndFeelDecorated(true);
		this.inputArgs = args;	
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		createGUI(); 
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
			textAreaLog.setText("");
			//if inputs are valid
			if (checkInputs()){
				//calculate the standard deviation
				double std = 0.33*Integer.parseInt(textDailyMean.getText());
				String[] test = new String[9];
				test[0] =textRNGSEED.getText();
				test[1] =textQueueSize.getText();
				test[2] =textDailyMean.getText();
				test[3] = String.valueOf(std);
				test[4] =textFirst.getText();
				test[5] =textBusiness.getText();
				test[6] =textPremium.getText();
				test[7] =textEconomy.getText();
				test[8] =textCancellation.getText();
		        SimulationRunner.main(test);
		        
			}else{
				textAreaLog.setText( textAreaLog.getText() +"\n Inputs are not valid");
			}
			
		} //end simulation button pressed events
		else if (src == btnSetDefault){
		textAreaLog.setText("default values set");
		setInitialValues();
		}else if (src == btnChangeChart){
			changeDisplay();
		}
	}
	
	
	

	
	private void createGUI() { setSize(WIDTH, HEIGHT); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setLayout(new BorderLayout());
	System.out.println("start creating gui");
	setSize(WIDTH, HEIGHT);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    //Create various panels
    pnlOne = createPanel(Color.BLUE);
    pnlBtn = createPanel(Color.GRAY);
    pnlInputs = createPanel(Color.GREEN);

    //create various buttons
    btnRunSimulation = createButton("Run Simulation");
    btnChangeChart = createButton("Change Display");
    btnSetDefault = createButton("Set Default Values");
    
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
   
    //create Text Area
     textAreaLog = createTextArea();
	
    //draw panels
    this.getContentPane().add(pnlOne,BorderLayout.CENTER);
    this.getContentPane().add(pnlBtn,BorderLayout.SOUTH);
    this.getContentPane().add(pnlInputs,BorderLayout.NORTH);


    //draw buttons to screen
    layoutButtonPanel();
    
    //Draw labels and text input to screen
    layoutInputsPanel();
    
    //Draw Text Area
    textAreaLog.setSize(50, 50);
    this.getContentPane().add(textAreaLog,BorderLayout.CENTER);
    
    //set GUI as visible
    repaint(); 
    this.setVisible(true);
    
    setToDefault();

	}
	
	
	
	//********************************************************************
	// used to create components for the GUI, from week 8 lab
	//******************************************************************
	
	//creates text field
	private JTextField createTextField() {
		JTextField jta = new JTextField("Enter Value Here");
		jta.setFont(new Font("Arial",Font.PLAIN,12));
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
	
	//create the text area
	private JTextArea createTextArea() {
		JTextArea jta = new JTextArea(); 
		jta.setEditable(false);
		jta.setLineWrap(true);
		jta.setFont(new Font("Arial",Font.BOLD,12));
		jta.setBorder(BorderFactory.createEtchedBorder());
		jta.setText("Will paste log here once ran");
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
	    addToPanel(pnlBtn, btnSetDefault,constraints,6,0,2,1); 

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
   
	
	private boolean checkInputs(){
		boolean inputsValidNum = (checkRNGSEED()&&
				checkQueueSize()&&
				checkFirst()&&
				checkPremium()&&
				checkDailyMean()&&
				checkCancellation()&&
				checkBusiness()&&
				checkEconomy());
				
		if (!inputsValidNum){
			return false;
		}
		
		return (checkProbabilitiesAddUpToOne());		
		
	}
	
	
	//check that all the probabilities of passengers selecting a fare
	//class sum to one.
	private boolean checkProbabilitiesAddUpToOne(){
		
		double firstProb = Double.parseDouble(textFirst.getText());
		double businessProb = Double.parseDouble(textBusiness.getText());
		double economyProb = Double.parseDouble(textEconomy.getText());
		double premiumProb = Double.parseDouble(textPremium.getText());
		double total = (firstProb + businessProb + economyProb+premiumProb);
		return(total==1);
	}
	
	
	//check the provided value for RNGSEED is valid
	private boolean checkRNGSEED(){
		String Input = textRNGSEED.getText();
		//ensure RNGSEED is an integer
		try {
			Integer.parseInt(Input);
		}
		catch (NumberFormatException e) {
			textAreaLog.setText( textAreaLog.getText() +"\n RNGSEED not valid");
			return false;
		}
		return true;

	}
   
	//check the provided value for QueueSize is valid
	private boolean checkQueueSize(){
		String Input = textQueueSize.getText();
		//ensure RNGSEED is an integer
		try {
			Integer.parseInt(Input);
		}
		catch (NumberFormatException e) {
			textAreaLog.setText( textAreaLog.getText() +"\n QueueSize not valid");
			return false;
		}
		return true;

	}
	//check the provided value for probability of booking first class is valid
	private boolean checkFirst(){
		String Input = textFirst.getText();
		//ensure RNGSEED is an integer
		try {
			double chance = Double.parseDouble(Input);
			if(chance>=0 && chance<=1){
				return true;
			}else{
				textAreaLog.setText( textAreaLog.getText() +"\n First not valid must be between 0 and 1");
			}
		}
		catch (NumberFormatException e) {
			textAreaLog.setText( textAreaLog.getText() +"\n First not valid");
			return false;
		}
		
		return true;
	}
	
	
	//check the provided value for probability of selecting premium is valid
	private boolean checkPremium(){
		String Input = textPremium.getText();
		//ensure RNGSEED is an integer
		try {
			double chance = Double.parseDouble(Input);
			if(chance>=0 && chance<=1){
				return true;
			}else{
				textAreaLog.setText( textAreaLog.getText() +"\n Premium not valid must be between 0 and 1");
			}
		}
		catch (NumberFormatException e) {
			textAreaLog.setText( textAreaLog.getText() +"\n Premium not valid");
			return false;
		}
		
		return true;
	}
  	
	//check the number provided for the daily mean is valid
	private boolean checkDailyMean(){
		String Input = textDailyMean.getText();
		//ensure RNGSEED is an integer
		try {
			Integer.parseInt(Input);
		}
		catch (NumberFormatException e) {
			textAreaLog.setText( textAreaLog.getText() +"\n DailyMean not valid");
			return false;
		}
		
		return true;
	}
	
	//check that the chance of cancellation is a valid percentatge
	private boolean checkCancellation(){
		String Input = textCancellation.getText();
		//ensure RNGSEED is an integer
		try {
			double chance = Double.parseDouble(Input);
			if(chance>=0 && chance<=1){
				return true;
			}else{
				textAreaLog.setText( textAreaLog.getText() +"\n Cancellation not valid must be between 0 and 1");
			}
		}
		catch (NumberFormatException e) {
			textAreaLog.setText( textAreaLog.getText() +"\n Cancellation not valid");
			return false;
		}
		
		return true;
	}
	
	//check the the probability of booking a business seat is valid
	private boolean checkBusiness(){
		String Input = textBusiness.getText();
		//ensure RNGSEED is an integer
		try {
			double chance = Double.parseDouble(Input);
			if(chance>=0 && chance<=1){
				return true;
			}else{
				textAreaLog.setText( textAreaLog.getText() +"\n Business not valid must be between 0 and 1");
			}
		}
		catch (NumberFormatException e) {
			textAreaLog.setText( textAreaLog.getText() +"\n Business not valid");
			return false;
		}
		
		return true;
	}
	
	//check the the probability of booking an Economy seat is valid
	private boolean checkEconomy(){
		String Input = textEconomy.getText();
		//ensure RNGSEED is an integer
		try {
			double chance = Double.parseDouble(Input);
			if(chance>=0 && chance<=1){
				return true;
			}else{
				textAreaLog.setText( textAreaLog.getText() +"\n Economy not valid must be between 0 and 1");
			}
		}
		catch (NumberFormatException e) {
			textAreaLog.setText( textAreaLog.getText() +"\n Economy not valid");
			return false;
		}
		
		return true;
	}
	
	
	//sets values in the GUI fields to the default values provided by Jim
	private void setToDefault(){
		textRNGSEED.setText("100");
		textQueueSize.setText("500");
		textFirst.setText("0.03");
		textPremium.setText("0.13");
		textDailyMean.setText("1300");
		textCancellation.setText("0.10");
		textBusiness.setText("0.14");
		textEconomy.setText("0.70");
	
	}
	
	//Rotates between the output log, and each of the graphs
	private void changeDisplay(){
		if (textAreaLog.isVisible()){
			textAreaLog.setVisible(false);
		}else {
			textAreaLog.setVisible(true);
		}
	}
	
	private void setInitialValues(){
		if (this.inputArgs.length==0){
		setToDefault();
		}{
			textRNGSEED.setText(this.inputArgs[0]);
			textQueueSize.setText(this.inputArgs[1]);
			textDailyMean.setText(this.inputArgs[2]);
			textFirst.setText(this.inputArgs[4]);
			textBusiness.setText(this.inputArgs[5]);
			textPremium.setText(this.inputArgs[6]);
			textEconomy.setText(this.inputArgs[7]);
			textCancellation.setText(this.inputArgs[8]);
		}
	}
	
	public void writeDataToScreen(){
		//addTextTooScreen(mySim.)
		//addTextTooScreen(mySim.getSummary(time, flying));
		
		
	}
	
	public void addTextTooScreen(String s){
		textAreaLog.setText(textAreaLog.getText() + s);
	}
	
}
