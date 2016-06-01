/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;
import asgn2Aircraft.A380;
import asgn2Aircraft.Aircraft;
import asgn2Aircraft.AircraftException;

/**
 * @author Nathan
 *
 */
public class A380Tests {
	
	/**
	 * Test methods for constructor with default A380 values.
	 * {@link asgn2Aircraft.A380#A380(java.lang.String, int)}
	 * @throws AircraftException 
	 */
	
	@Test(expected=AircraftException.class)
	public void testA380FlightcodeNull() throws AircraftException {
		Aircraft plane = new A380(null, 1);
		plane.flightEmpty();
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testA380FlightcodeEmpty() throws AircraftException {
		Aircraft plane = new A380("", 1);
		plane.flightEmpty();
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testA380NegativeDeparturetime() throws AircraftException {
		Aircraft plane = new A380("BiqBoi69", -1);
		plane.flightEmpty();
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testA380ZeroDeparturetime() throws AircraftException {
		Aircraft plane = new A380("MileHighClub", 0);
		plane.flightEmpty();
		fail("Exception expected");
	}
	
	@Test
	public void testA380Valid() throws AircraftException {
		Aircraft plane = new A380("MileHighClub", 1);
		if((plane.flightEmpty() && !plane.flightFull())
			&& (plane.getNumBusiness() == 0) && (plane.getNumFirst() == 0)
			&& (plane.getNumPremium() == 0) && (plane.getNumEconomy() == 0)) {
			assertTrue(true);
		} else {
			fail("All seat values should be equal to standard A380 amount");
		}
	}
	


	/**
	 * Test methods for constructor with user-specified A380 values.
	 * {@link asgn2Aircraft.A380#A380(java.lang.String, int, int, int, int, int)}
	 * @throws AircraftException 
	 */
	
	@Test(expected=AircraftException.class)
	public void testA38SpecNullString() throws AircraftException {
		Aircraft plane = new A380(null, 1,2,3,4,5);
		plane.flightEmpty();
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testA380SpecZeroDeparturetime() throws AircraftException {
		Aircraft plane = new A380("Planey McPlaneFace", 0,2,3,4,5);
		plane.flightEmpty();
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testA380NegativeFirst() throws AircraftException {
		Aircraft plane = new A380("NegFirst", 1,-1,3,4,5);
		plane.flightEmpty();
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testA380NegativeBusiness() throws AircraftException {
		Aircraft plane = new A380("My Marks for this subject are like this plane, they won't make it off the ground", 1,2,-3,4,5);
		plane.flightEmpty();
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testA380NegativePremium() throws AircraftException {
		Aircraft plane = new A380("NegPrem", 1,2,3,-4,5);
		plane.flightEmpty();
		fail("Exception expected");
	}
	
	@Test(expected=AircraftException.class)
	public void testA380NegativeEconomy() throws AircraftException {
		Aircraft plane = new A380("NoPeasantsAllowed", 1,2,3,4,-5);
		plane.flightEmpty();
		fail("Exception expected");
	}
	
	@Test
	public void testA380SpecValid() throws AircraftException {
		Aircraft plane = new A380("MileHighClub", 1, 2, 3, 4, 5);
		if((plane.flightEmpty() && !plane.flightFull())
			&& (plane.getNumBusiness() == 0) && (plane.getNumFirst() == 0)
			&& (plane.getNumPremium() == 0) && (plane.getNumEconomy() == 0)) {
			assertTrue(true);
		} else {
			fail("All seat values should be equal to standard A380 amount");
		}
	}

}
