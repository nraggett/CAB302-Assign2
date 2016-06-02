package asgn2Simulators;

import javax.swing.SwingUtilities;

public class GuiSimulatorTester {

	public static void main(){
		
		String[] argstest = new String[9];
		argstest[0]="1";
		argstest[1]="1";
		argstest[2]="1";
		argstest[3]="1";
		argstest[4]="1";
		argstest[5]="1";
		argstest[6]="1";
		argstest[7]="1";
		argstest[8]="1";
		SwingUtilities.invokeLater(new GUISimulator("BorderLayout", argstest));
		
	}
	
}


