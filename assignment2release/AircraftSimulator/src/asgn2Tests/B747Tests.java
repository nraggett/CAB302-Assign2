/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;
import asgn2Aircraft.B747;
import asgn2Aircraft.B747;
import asgn2Aircraft.Aircraft;
import asgn2Aircraft.AircraftException;

/**
 * @author Nathan
 *
 */
public class B747Tests {

	/**
	 * Test methods for constructor with default B747 values.
	 * {@link asgn2Aircraft.B747#B747(java.lang.String, int)}
	 * @throws AircraftException 
	 */
	
	@Test(expected=AircraftException.class)
	public void testB747FlightcodeNull() throws AircraftException {
		B747 plane = new B747(null, 1);
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testB747FlightcodeEmpty() throws AircraftException {
		B747 plane = new B747("", 1);
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testB747NegativeDeparturetime() throws AircraftException {
		B747 plane = new B747("BiqBoi69", -1);
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testB747ZeroDeparturetime() throws AircraftException {
		B747 plane = new B747("MileHighClub", 0);
		fail("Exception expected");
	}
	
	@Test
	public void testB747Valid() throws AircraftException {
		B747 plane = new B747("MileHighClub", 1);
		if((plane.flightEmpty() && !plane.flightFull())
			&& (plane.getNumBusiness() == 0) && (plane.getNumFirst() == 0)
			&& (plane.getNumPremium() == 0) && (plane.getNumEconomy() == 0)) {
			assertTrue(true);
		} else {
			fail("All seat values should be equal to standard B747 amount");
		}
	}
	


	/**
	 * Test methods for constructor with user-specified B747 values.
	 * {@link asgn2Aircraft.B747#B747(java.lang.String, int, int, int, int, int)}
	 * @throws AircraftException 
	 */
	
	@Test(expected=AircraftException.class)
	public void testA38SpecNullString() throws AircraftException {
		B747 plane = new B747(null, 1,2,3,4,5);
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testB747SpecZeroDeparturetime() throws AircraftException {
		B747 plane = new B747("Planey McPlaneFace", 0,2,3,4,5);
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testB747NegativeFirst() throws AircraftException {
		B747 plane = new B747("NegFirst", 1,-1,3,4,5);
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testB747NegativeBusiness() throws AircraftException {
		B747 plane = new B747("My Marks for this subject are like this plane, they won't make it off the ground", 1,2,-3,4,5);
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testB747NegativePremium() throws AircraftException {
		B747 plane = new B747("NegPrem", 1,2,3,-4,5);
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testB747NegativeEconomy() throws AircraftException {
		B747 plane = new B747("NoPeasantsAllowed", 1,2,3,4,-5);
		fail("Exception expected");
	}
	
	@Test
	public void testB747SpecValid() throws AircraftException {
		B747 plane = new B747("MileHighClub", 1, 2, 3, 4, 5);
		if((plane.flightEmpty() && !plane.flightFull())
			&& (plane.getNumBusiness() == 0) && (plane.getNumFirst() == 0)
			&& (plane.getNumPremium() == 0) && (plane.getNumEconomy() == 0)) {
			assertTrue(true);
		} else {
			fail("All seat values should be equal to standard B747 amount");
		}
	}

}
