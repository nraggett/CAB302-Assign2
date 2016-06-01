/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import asgn2Aircraft.A380;
import asgn2Aircraft.Aircraft;
import asgn2Aircraft.AircraftException;
import asgn2Aircraft.Bookings;
import asgn2Passengers.Business;
import asgn2Passengers.Economy;
import asgn2Passengers.First;
import asgn2Passengers.Passenger;
import asgn2Passengers.PassengerException;
import asgn2Passengers.Premium;

/**
 * @author Nathan
 *
 */
public class AircraftTests {

	/**
	 * Test methods for constructor with user-specified Aircraft values are handled in child classes.
	 * {@link asgn2Aircraft.Aircraft#Aircraft(java.lang.String, int, int, int, int, int)}
	 * is tested in: 
	 * asgn2Tests.A380Tests.testA380SpecValid()
	 * and asgn2Tests.B747Tests.testB747SpecValid()
	 * @throws AircraftException 
	 */
	
	
	/**
	 * Test method for cancelling a passenger booking.
	 * We're only testing for AircraftException, as PassengerException is handled
	 * on a per-passenger basis.
	 * {@link asgn2Aircraft.Aircraft#cancelBooking(asgn2Passengers.Passenger, int)}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test(expected=AircraftException.class)
	public void testCancelBookingNotBooked() throws AircraftException, PassengerException {
		A380 plane = new A380("testing, testing, 1,2,3", 1);
		Passenger classyguy = new First(1, 2);
		plane.cancelBooking(classyguy, 1);
		fail("Exception Expected");
	}
	
	@Test
	public void testCancelBookingValid() throws AircraftException, PassengerException {
		A380 plane = new A380("testing, testing, 1,2,3", 1);
		Passenger classyguy = new First(1, 2);
		plane.confirmBooking(classyguy, 1);
		plane.cancelBooking(classyguy, 1);
//		If no exceptions
		assertTrue(true);
	}

	/**
	 * Test method for confirming a passenger booking.
	 * We're only testing for AircraftException, as PassengerException is handled
	 * on a per-passenger basis.
	 * {@link asgn2Aircraft.Aircraft#confirmBooking(asgn2Passengers.Passenger, int)}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test(expected=AircraftException.class)
	public void testConfirmBookingNoSeats() throws AircraftException, PassengerException {
		Aircraft plane = new A380("No Peasants", 1, 2, 3, 4, 0);
		Passenger BillGates = new Economy(1, 1);
		plane.confirmBooking(BillGates, 1);
		fail("Exception Expected");
	}

	/**
	 * Test method supplied by Jim Hogan, no testing needed.
	 * {@link asgn2Aircraft.Aircraft#finalState()}.
	 */
	@Test
	public void testFinalState() {
		assertTrue(true);
	}

	/**
	 * Test method for checking if the flight has no passengers.
	 * {@link asgn2Aircraft.Aircraft#flightEmpty()}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test
	public void testFlightEmptyFalse() throws AircraftException, PassengerException {
		Aircraft plane = new A380("No Peasants", 1, 2, 3, 4, 0);
		Passenger BillGates = new First(1, 1);
		plane.confirmBooking(BillGates, 1);
		if(!plane.flightEmpty()){
			assertTrue(true);
		} else {
			fail("Should return false");
		}
	}
	
	@Test
	public void testFlightEmptyTrue() throws AircraftException, PassengerException {
		Aircraft plane = new A380("No Peasants", 1, 2, 3, 4, 0);
		if(plane.flightEmpty()){
			assertTrue(true);
		} else {
			fail("Should return true");
		}
	}

	/**
	 * Test method for checking if the flight is fully loaded.
	 * {@link asgn2Aircraft.Aircraft#flightFull()}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test
	public void testFlightFullFalse() throws AircraftException, PassengerException {
		Aircraft plane = new A380("No Peasants", 1, 2, 3, 4, 0);
		Passenger BillGates = new First(1, 1);
		plane.confirmBooking(BillGates, 1);
		if(!plane.flightFull()){
			assertTrue(true);
		} else {
			fail("Should return false");
		}
	}
	
	@Test
	public void testFlightFullTrue() throws AircraftException, PassengerException {
		Aircraft plane = new A380("No Peasants", 1, 1, 0, 0, 0);
		Passenger BillGates = new First(1, 1);
		plane.confirmBooking(BillGates, 1);
		if(plane.flightFull()){
			assertTrue(true);
		} else {
			fail("Should return true");
		}
	}

	/**
	 * Test method for setting all passengers on board to flown.
	 * {@link asgn2Aircraft.Aircraft#flyPassengers(int)}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test
	public void testFlyPassengersTrue() throws AircraftException, PassengerException {
		Aircraft plane = new A380("No Peasants", 1, 1, 0, 0, 0);
		Passenger BillGates = new First(1, 1);
		plane.confirmBooking(BillGates, 1);
		plane.flyPassengers(1);
		if(BillGates.isFlown()){
			assertTrue(true);
		} else {
			fail("Should return true");
		}
	}
	
	@Test
	public void testFlyPassengersFalse() throws AircraftException, PassengerException {
		Aircraft plane = new A380("No Peasants", 1, 1, 0, 0, 0);
		Passenger BillGates = new First(1, 1);
		plane.confirmBooking(BillGates, 1);
		if(!BillGates.isFlown()){
			assertTrue(true);
		} else {
			fail("Should return false");
		}
	}

	/**
	 * Test method for 
	 * {@link asgn2Aircraft.Aircraft#getBookings()}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test
	public void testGetBookings() throws AircraftException, PassengerException {
		Aircraft plane = new A380("Testy McTestFace", 1, 1, 2, 3, 4);
		Passenger DatBoi = new First(1, 1);
		plane.confirmBooking(DatBoi, 1);
		Passenger Homeless = new Economy(1, 1);
		plane.confirmBooking(Homeless, 1);
		Bookings testbooking = plane.getBookings();
		if((testbooking.getNumFirst() == 1) && (testbooking.getNumBusiness() == 0)
			&& (testbooking.getNumPremium() == 0) && (testbooking.getNumEconomy() == 1)
			&& (testbooking.getAvailable() == (1+2+3+4-2))){
			assertTrue(true);
		} else {
			fail("Should return true");
		}
	}

	/**
	 * Test method for getting the number of Confirmed Business
	 * Class Passengers on board.
	 * {@link asgn2Aircraft.Aircraft#getNumBusiness()}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test
	public void testGetNumBusiness() throws AircraftException, PassengerException {
		Aircraft plane = new A380("Testy McTestFace", 1, 1, 2, 3, 4);
		Passenger DatBoi = new First(1, 1);
		plane.confirmBooking(DatBoi, 1);
		Passenger Homeless = new Business(1, 1);
		plane.confirmBooking(Homeless, 1);
		if(plane.getNumBusiness() == 1){
			assertTrue(true);
		} else {
			fail("Should return true");
		}
	}

	/**
	 * Test method for getting the number of Confirmed Economy
	 * Class Passengers on board.
	 *{@link asgn2Aircraft.Aircraft#getNumEconomy()}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test
	public void testGetNumEconomy() throws AircraftException, PassengerException {
		Aircraft plane = new A380("Testy McTestFace", 1, 1, 2, 3, 4);
		Passenger DatBoi = new Business(1, 1);
		plane.confirmBooking(DatBoi, 1);
		Passenger Homeless = new Economy(1, 1);
		plane.confirmBooking(Homeless, 1);
		if(plane.getNumEconomy() == 1){
			assertTrue(true);
		} else {
			fail("Should return true");
		}
	}

	/**
	 * Test method for getting the number of Confirmed First
	 * Class Passengers on board
	 * {@link asgn2Aircraft.Aircraft#getNumFirst()}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test
	public void testGetNumFirst() throws AircraftException, PassengerException {
		Aircraft plane = new A380("Testy McTestFace", 1, 1, 2, 3, 4);
		Passenger DatBoi = new Premium(1, 1);
		plane.confirmBooking(DatBoi, 1);
		Passenger Homeless = new First(1, 1);
		plane.confirmBooking(Homeless, 1);
		if(plane.getNumFirst() == 1){
			assertTrue(true);
		} else {
			fail("Should return true");
		}
	}

	/**
	 * Test method for getting the number of Confirmed Passengers on board
	 * {@link asgn2Aircraft.Aircraft#getNumPassengers()}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test
	public void testGetNumPassengers() throws AircraftException, PassengerException {
		Aircraft plane = new A380("Testy McTestFace", 1, 1, 2, 3, 4);
		Passenger DatBoi = new Premium(1, 1);
		plane.confirmBooking(DatBoi, 1);
		Passenger Homeless = new First(1, 1);
		plane.confirmBooking(Homeless, 1);
		if(plane.getNumPassengers() == 2){
			assertTrue(true);
		} else {
			fail("Should return true");
		}
	}

	/**
	 * Test method for getting the number of Confirmed First
	 * Class Passengers on board
	 * {@link asgn2Aircraft.Aircraft#getNumPremium()}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test
	public void testGetNumPremium() throws AircraftException, PassengerException {
		Aircraft plane = new A380("Testy McTestFace", 1, 1, 2, 3, 4);
		Passenger DatBoi = new Premium(1, 1);
		plane.confirmBooking(DatBoi, 1);
		Passenger Homeless = new First(1, 1);
		plane.confirmBooking(Homeless, 1);
		if(plane.getNumPremium() == 1){
			assertTrue(true);
		} else {
			fail("Should return true");
		}
	}

	/**
	 * Test method for getting the list of Passengers on board
	 * {@link asgn2Aircraft.Aircraft#getPassengers()}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test
	public void testGetPassengers() throws AircraftException, PassengerException {
		Aircraft plane = new A380("Testy McTestFace", 1, 1, 2, 3, 4);
		Passenger DatBoi = new Premium(1, 1);
		plane.confirmBooking(DatBoi, 1);
		Passenger Homeless = new First(1, 1);
		plane.confirmBooking(Homeless, 1);
		List<Passenger> testlist = plane.getPassengers();
		if(testlist.contains(DatBoi) && testlist.contains(Homeless)){
			assertTrue(true);
		} else {
			fail("Should return true");
		}
	}

	/**
	 * Method supplied by Jim Hogan, no need to test. 
	 * {@link asgn2Aircraft.Aircraft#getStatus(int)}.
	 */
	@Test
	public void testGetStatus() {
		assertTrue(true);
	}

	/**
	 * Test method for determining if a passenger is on board the aircraft.
	 * {@link asgn2Aircraft.Aircraft#hasPassenger(asgn2Passengers.Passenger)}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test
	public void testHasPassenger() throws AircraftException, PassengerException {
		Aircraft plane = new A380("Testy McTestFace", 1, 1, 2, 3, 4);
		Passenger DatBoi = new Premium(1, 1);
		plane.confirmBooking(DatBoi, 1);
		Passenger Homeless = new First(1, 1);
		if(plane.hasPassenger(DatBoi) && !plane.hasPassenger(Homeless)){
			assertTrue(true);
		} else {
			fail("Should return true");
		}
	}

	/**
	 * Method supplied by Jim Hogan, no need to test. 
	 * {@link asgn2Aircraft.Aircraft#initialState()}.
	 */
	@Test
	public void testInitialState() {
		assertTrue(true);
	}

	/**
	 * Test method for determining if any seats are left for a passenger's class.
	 * {@link asgn2Aircraft.Aircraft#seatsAvailable(asgn2Passengers.Passenger)}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test
	public void testSeatsAvailable() throws AircraftException, PassengerException {
		Aircraft plane = new A380("Testy McTestFace", 1, 1, 2, 3, 4);
		Passenger DatBoi = new First(1, 1);
		plane.confirmBooking(DatBoi, 1);
		Passenger Homeless = new Economy(1, 1);
		plane.confirmBooking(Homeless, 1);
		if(plane.seatsAvailable(Homeless) && !plane.seatsAvailable(DatBoi)){
			assertTrue(true);
		} else {
			fail("Should return true");
		}
	}

	/**
	 * Method supplied by Jim Hogan, no need to test.
	 * {@link asgn2Aircraft.Aircraft#toString()}.
	 */
	@Test
	public void testToString() {
		assertTrue(true);
	}

	/**
	 * Test method for filling classes above passengers in order to fit more passengers.
	 * {UNDERWAY}
	 * {@link asgn2Aircraft.Aircraft#upgradeBookings()}.
	 * @throws AircraftException 
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgradeBookings() throws AircraftException, PassengerException {
		Aircraft plane = new A380("Testy McTestFace", 1, 1, 2, 3, 4);
		Passenger DatBoi = new First(1, 1);
		plane.confirmBooking(DatBoi, 1);
		Passenger Homeless = new Economy(1, 1);
		plane.confirmBooking(Homeless, 1);
		plane.upgradeBookings();
		if(plane.getNumEconomy() == 0 && plane.getNumFirst() == 1 && plane.getNumPremium() == 1){
			assertTrue(true);
		} else {
			fail("Should return true");
		}
	}

}
