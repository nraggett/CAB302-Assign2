/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Premium;

/**
 * @author Mitchell
 *
 */

public class PremiumTests {

	//****************************************************************************************
	// Test methods for asgn2Passengers.Premium#noSeatsMsg()
	//****************************************************************************************
	
	/**
	 * Test method for {@link asgn2Passengers.Premium#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsgDontTest() {
		assertTrue(true);
	}
	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Premium#upgrade()
	//****************************************************************************************

	
	/**
	 * Test method for {@link asgn2Passengers.Premium#upgrade()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgradeAltersPassengerIDLetter() throws PassengerException {
		Premium testPassenger = new Premium(10,30);
		Passenger pnew = testPassenger.upgrade();
		assertTrue(pnew.getPassID().charAt(0)=='J');
	}
	
	
	
	/**
	 * Test method for {@link asgn2Passengers.Premium#upgrade()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgradeNewPassengerPassIDIsValidLength() throws PassengerException {
		Premium testPassenger = new Premium(10,30);
		Passenger pnew = testPassenger.upgrade();
		assertTrue(pnew.getPassID().length()==testPassenger.getPassID().length());
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Premium#upgrade()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgradeAltersPassengerPassIDExtension() throws PassengerException {
		Premium testPassenger = new Premium(10,30);
		Passenger pNew = testPassenger.upgrade();
		int passIDLength = testPassenger.getPassID().length()-1;
		
		String testPassengerExtension = testPassenger.getPassID().substring(1,passIDLength);
		String pNewExtension = pNew.getPassID().substring(1,passIDLength);
		
		assertTrue(testPassengerExtension!=pNewExtension);
	}
	
	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Premium#Premium(int,int)
	//****************************************************************************************
	

	/**
	 * Test method for {@link asgn2Passengers.Premium#Premium(int, int)}.
	 */
	@Test
	public void testPremiumIntIntDontTest() {
		assertTrue(true);
	}
	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Premium#Premium()
	//****************************************************************************************

	/**
	 * Test method for {@link asgn2Passengers.Premium#Premium()}.
	 */
	@Test
	public void testPremiumDontTest() {
		assertTrue(true);
	}

}
