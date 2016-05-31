/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Passengers.Business;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;

/**
 * @author Mitchell
 * 
 */


public class BusinessTests {

	//****************************************************************************************
	// Test methods for asgn2Passengers.Business#noSeatsMsg()
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.Business#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsgDontTest() {
		assertTrue(true);
	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.Business#upgrade()
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.Business#upgrade()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgradeAltersPassengerID() throws PassengerException {
		Business testPassenger = new Business(10,30);
		Passenger pnew = testPassenger.upgrade();
		assertTrue(pnew.getPassID().charAt(0)=='F');
	}
	
	
	
	/**
	 * Test method for {@link asgn2Passengers.Business#upgrade()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgradeNewPassengerPassIDIsValidLength() throws PassengerException {
		Business testPassenger = new Business(10,30);
		Passenger pnew = testPassenger.upgrade();
		assertTrue(pnew.getPassID().length()==testPassenger.getPassID().length());
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Business#upgrade()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgradeAltersPassengerPassIDExtension() throws PassengerException {
		Business testPassenger = new Business(10,30);
		Passenger pNew = testPassenger.upgrade();
		int passIDLength = testPassenger.getPassID().length()-1;
		
		String testPassengerExtension = testPassenger.getPassID().substring(1,passIDLength);
		String pNewExtension = pNew.getPassID().substring(1,passIDLength);
		
		assertTrue(testPassengerExtension!=pNewExtension);
	}

	
	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Business#Business(int,int)
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.Business#Business(int, int)}.
	 */
	@Test
	public void testBusinessIntIntDontTest() {
		assertTrue(true);
	}

	
	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Business#upgrade()
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.Business#Business()}.
	 */
	@Test
	public void testBusinessDontTest() {
		assertTrue(true);
	}

}
