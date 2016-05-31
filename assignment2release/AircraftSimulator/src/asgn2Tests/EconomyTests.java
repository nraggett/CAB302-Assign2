/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Passengers.Business;
import asgn2Passengers.Economy;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;

/**
 * @author Mitchell
 *
 */
public class EconomyTests {

	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Economy#noSeatsMsg()
	//****************************************************************************************
	
	/**
	 * Test method for {@link asgn2Passengers.Economy#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsgDontTest() {
		assertTrue(true);
	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.Economy#upgrade()
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.Economy#upgrade()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgradeAltersPassengerIDLetter() throws PassengerException {
		Economy testPassenger = new Economy(10,30);
		Passenger pnew = testPassenger.upgrade();
		assertTrue(pnew.getPassID().charAt(0)=='P');
	}
	
	
	
	/**
	 * Test method for {@link asgn2Passengers.Economy#upgrade()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgradeNewPassengerPassIDIsValidLength() throws PassengerException {
		Economy testPassenger = new Economy(10,30);
		Passenger pnew = testPassenger.upgrade();
		assertTrue(pnew.getPassID().length()==testPassenger.getPassID().length());
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Economy#upgrade()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgradeAltersPassengerPassIDExtension() throws PassengerException {
		Economy testPassenger = new Economy(10,30);
		Passenger pNew = testPassenger.upgrade();
		int passIDLength = testPassenger.getPassID().length()-1;
		
		String testPassengerExtension = testPassenger.getPassID().substring(1,passIDLength);
		String pNewExtension = pNew.getPassID().substring(1,passIDLength);
		
		assertTrue(testPassengerExtension!=pNewExtension);
	}

	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Economy#Economy(int,int)
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.Economy#Economy(int, int)}.
	 */
	@Test
	public void testEconomyDontTest() {
		assertTrue(true);
	}

}
