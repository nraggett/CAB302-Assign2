/**
 * 
 */
package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import asgn2Passengers.Business;
import asgn2Passengers.First;
import asgn2Passengers.PassengerException;

/**
 * @author Mitchell
 *
 */
public class FirstTests {

	
	//****************************************************************************************
	// Test methods for asgn2Passengers.First#noSeatsMsg()
	//****************************************************************************************
	
	/**
	 * Test method for {@link asgn2Passengers.First#noSeatsMsg()}.
	 */
	@Test
	public void testNoSeatsMsgDontTest() {
		assertTrue(true);
	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.First#upgrade()
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.First#upgrade()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testUpgrade() throws PassengerException {
		First testPassenger = new First(10,30);
		assertTrue(null== testPassenger.upgrade() );

	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.First#First(int,int)
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.First#First(int, int)}.
	 */
	@Test
	public void testFirstIntIntDontTest() {
		assertTrue(true);
	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.First#First()
	//****************************************************************************************
	
	/**
	 * Test method for {@link asgn2Passengers.First#First()}.
	 */
	@Test
	public void testFirst() {
		assertTrue(true);
	}

//********************************************************************************************
//********************************************************************************************
// Test methods for passenger class start here
//********************************************************************************************
//********************************************************************************************
	
	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#Passenger()
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger(int, int)}.
	 */
	@Test
	public void testPassengerIntIntDontTest() {
		assertTrue(true);
	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#Passenger()
	//****************************************************************************************
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#Passenger()}.
	 */
	@Test
	public void testPassengerDontTest() {
		assertTrue(true);
	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#cancelSeat()
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test (expected = PassengerException.class)
	public void testCancelSeatPassengerCacellationTimeZero() throws PassengerException {
		Business p = new Business(1,12);
		p.cancelSeat(0); //will always be less then booking time
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testCancelSeatPassengerRefuseTimeNegative() throws PassengerException {
		Business p = new Business(3,12);
		p.cancelSeat(-5);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testCancelSeatPassengerCancellationTimeAfterDepartureTime() throws PassengerException {
		Business p = new Business(10,12);
		p.cancelSeat(13);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test (expected = PassengerException.class)
	public void testCancelSeatPassengerStateIsNew() throws PassengerException {
		Business p = new Business(10,12);
		p.cancelSeat(11);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test (expected = PassengerException.class)
	public void testCancelSeatPassengerStateIsQueued() throws PassengerException {
		Business p = createPassengerInQueue();
		p.cancelSeat(11);

		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testCancelSeatPassengerStateIsRefused() throws PassengerException {
		Business p = createPassengerRefused();
		p.cancelSeat(11);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test 
	public void testCancelSeatPassengerStateIsConfirmed() throws PassengerException {
		Business p = createPassengerConfirmed();
		p.cancelSeat(11);
		assertTrue(p.isNew());
		
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#cancelSeat(int)}.
	 * @throws PassengerException 
	 */
	@Test (expected = PassengerException.class)
	public void testCancelSeatPassengerStateIsFlown() throws PassengerException {
		Business p = createPassengerFlown();
		p.cancelSeat(11);
		
	}
	
	
	
	

	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#confirmSeat()
	//****************************************************************************************
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test (expected = PassengerException.class)
	public void testConfirmSeatConfirmationTimeLessThenZero() throws PassengerException {
		Business p = new Business(10,50);
		p.confirmSeat(-4, 5);
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test (expected = PassengerException.class)
	public void testConfirmSeatConfirmationAfterDepartureTime() throws PassengerException {
		Business p = new Business(10,50);
		p.confirmSeat(6, 5);
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testConfirmSeatPassengerStateIsNew() throws PassengerException {
		Business p = new Business(10,12);
		p.confirmSeat(10,20);
		assertTrue(p.isConfirmed());
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testConfirmSeatPassengerStateIsQueued() throws PassengerException {
		Business p = createPassengerInQueue();
		p.confirmSeat(10,20);
		assertTrue(p.isConfirmed());
		
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testConfirmSeatPassengerStateIsRefused() throws PassengerException {
		Business p = createPassengerRefused();
		p.confirmSeat(10,20);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test (expected= PassengerException.class)
	public void testConfirmSeatPassengerStateIsConfirmed() throws PassengerException {
		Business p = createPassengerConfirmed();
		p.confirmSeat(10,20);
		
	}
	

	/**
	 * Test method for {@link asgn2Passengers.Passenger#confirmSeat(int, int)}.
	 * @throws PassengerException 
	 */
	@Test (expected= PassengerException.class)
	public void testConfirmSeatPassengerStateIsFlown() throws PassengerException {
		Business p = createPassengerFlown();
		p.confirmSeat(10,20);
		
	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#flyPassenger()
	//****************************************************************************************
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testFlyPassengerDepartureTimeZero() throws PassengerException {
		Business p = new Business(10,12);
		p.flyPassenger(0);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testFlyPassengerDepartureTimeNegative() throws PassengerException {
		Business p = new Business(10,12);
		p.flyPassenger(-5);
		
	}
	

	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testFlyPassengerStateIsNew() throws PassengerException {
		Business p = new Business(10,12);
		p.flyPassenger(13);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testFlyPassengerStateIsQueued() throws PassengerException {
		Business p = createPassengerInQueue();
		p.flyPassenger(13);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testFlyPassengerStateIsRefused() throws PassengerException {
		Business p = createPassengerRefused();
		p.flyPassenger(13);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testFlyPassengerStateIsConfirmed() throws PassengerException {
		Business p = createPassengerConfirmed();
		p.flyPassenger(13);
		assertTrue(p.isFlown());
		
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#flyPassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test (expected = PassengerException.class)
	public void testFlyPassengerStateIsFlown() throws PassengerException {
		Business p = createPassengerFlown();
		p.flyPassenger(13);
		
	}
	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#getBookingTime()
	//****************************************************************************************
	/**
	 * Test method for {@link asgn2Passengers.Passenger#getBookingTime()}.
	 */
	@Test
	public void testGetBookingTimeDontTest() {
		assertTrue(true);
	}

	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#getConfirmationTime()
	//****************************************************************************************
	/**
	 * Test method for {@link asgn2Passengers.Passenger#getConfirmationTime()}.
	 */
	@Test
	public void testGetConfirmationTimeDontTest() {
		assertTrue(true);
	}
	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#getDepartureTime()
	//****************************************************************************************

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getDepartureTime()}.
	 */
	@Test
	public void testGetDepartureTimeDontTest() {
		assertTrue(true);
	}
	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#getEnterQueueTime()
	//****************************************************************************************

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getEnterQueueTime()}.
	 */
	@Test
	public void testGetEnterQueueTimeDontTest() {
		assertTrue(true);
	}
	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#getExitQueueTime()
	//****************************************************************************************

	/**
	 * Test method for {@link asgn2Passengers.Passenger#getExitQueueTime()}.
	 */
	@Test
	public void testGetExitQueueTimeDontTest() {
		assertTrue(true);
	}

	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#getPassID()
	//****************************************************************************************
	/**
	 * Test method for {@link asgn2Passengers.Passenger#getPassID()}.
	 */
	@Test
	public void testGetPassIDDontTest() {
		assertTrue(true);
	}

	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#isConfirmed()
	//****************************************************************************************
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#isConfirmed()}.
	 */
	@Test
	public void testIsConfirmedDontTest() {
		assertTrue(true);
	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#isFlown()
	//****************************************************************************************
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#isFlown()}.
	 */
	@Test
	public void testIsFlownDontTest() {
		assertTrue(true);
	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#isNew()
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#isNew()}.
	 */
	@Test
	public void testIsNewDontTest() {
		assertTrue(true);
	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#isQueued()
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#isQueued()}.
	 */
	@Test
	public void testIsQueuedDontTest() {
		assertTrue(true);
	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#isRefused()
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#isRefused()}.
	 */
	@Test
	public void testIsRefusedDontTest() {
		assertTrue(true);
	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#QueuePassenger()
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testQueuePassengerQueueTimeZero() throws PassengerException {
		Business p = new Business(10,12);
		p.queuePassenger(0, 10);
		assertTrue(p.isQueued());
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testQueuePassengerQueueTimeNegative() throws PassengerException {
		Business p = new Business(10,12);
		p.queuePassenger(-5, 10);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testQueuePassengerQueueTimeAfterDepartureTime() throws PassengerException {
		Business p = new Business(10,12);
		p.queuePassenger(5, 3);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testQueuePassengerStateIsNew() throws PassengerException {
		Business p = new Business(10,12);
		p.queuePassenger(2, 10);
		assertTrue(p.isQueued());
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testQueuePassengerStateIsQueued() throws PassengerException {
		Business p = createPassengerInQueue();
		p.queuePassenger(2, 10);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testQueuePassengerStateIsRefused() throws PassengerException {
		Business p = createPassengerRefused();
		p.queuePassenger(2, 10);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test (expected= PassengerException.class)
	public void testQueuePassengerStateIsConfirmed() throws PassengerException {
		Business p = createPassengerConfirmed();
		p.queuePassenger(2, 10);
		
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#queuePassenger(int, int)}.
	 * @throws PassengerException 
	 */
	@Test (expected = PassengerException.class)
	public void testQueuePassengerStateIsFlown() throws PassengerException {
		Business p = createPassengerFlown();
		p.queuePassenger(2, 10);
		
	}
	
	
	

	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#refusePassenger()
	//****************************************************************************************
		
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test (expected = PassengerException.class)
	public void testRefusePassengerRefuseTimeZero() throws PassengerException {
		Business p = new Business(1,12);
		p.refusePassenger(0); //will always be less then booking time
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testRefusePassengerRefuseTimeNegative() throws PassengerException {
		Business p = new Business(0,12);
		p.refusePassenger(-5);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testRefusePassengerRefuseTimeBeforeBookingTime() throws PassengerException {
		Business p = new Business(10,12);
		p.refusePassenger(9);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testRefusePassengerStateIsNew() throws PassengerException {
		Business p = new Business(10,12);
		p.refusePassenger(11);
		assertTrue(p.isRefused());
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test
	public void testRefusePassengerStateIsQueued() throws PassengerException {
		Business p = createPassengerInQueue();
		p.refusePassenger(11);
		assertTrue(p.isRefused());
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test(expected= PassengerException.class)
	public void testRefusePassengerStateIsRefused() throws PassengerException {
		Business p = createPassengerRefused();
		p.refusePassenger(11);
		
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test (expected= PassengerException.class)
	public void testRefusePassengerStateIsConfirmed() throws PassengerException {
		Business p = createPassengerConfirmed();
		p.refusePassenger(11);
		
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#refusePassenger(int)}.
	 * @throws PassengerException 
	 */
	@Test (expected = PassengerException.class)
	public void testRefusePassengerStateIsFlown() throws PassengerException {
		Business p = createPassengerFlown();
		p.refusePassenger(11);
		
	}
	
	
	

	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#toString()
	//****************************************************************************************
	/**
	 * Test method for {@link asgn2Passengers.Passenger#toString()}.
	 */
	@Test
	public void testToStringDontTest() {
		assertTrue(true);
	}

	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#wasConfirmed()
	//****************************************************************************************
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasConfirmed()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasConfirmedPassengerNewAndNeverConfirmed() throws PassengerException {
		Business p = new Business(10,40);
		assertTrue(!p.wasConfirmed());
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasConfirmed()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasConfirmedPassengerNewAndOnceConfirmed() throws PassengerException {
		Business p = createPassengerConfirmed();
		p.cancelSeat(12);
		assertTrue(p.wasConfirmed());
	}

	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasConfirmed()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasConfirmedPassengerInQueueNeverConfirmed() throws PassengerException {
		//create a passenger currently in queue
		Business p = createPassengerInQueue();
		assertTrue(!p.wasConfirmed());
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasConfirmed()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasConfirmedPassengerCurrentlyConfirmed() throws PassengerException {
		//create a passenger currently in queue
		Business p = createPassengerConfirmed();
		assertTrue(p.wasConfirmed());
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasConfirmed()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasConfirmedPassengerRefusedNeverConfirmed() throws PassengerException {
		//create a passenger currently in queue
		Business p = createPassengerRefused();
		assertTrue(!p.wasConfirmed());
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasConfirmed()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasConfirmedPassengerRefusedOnceConfirmed() throws PassengerException {
		//create a passenger currently in queue
		Business p = createPassengerConfirmed();
		p.cancelSeat(20);
		p.refusePassenger(20);
		assertTrue(p.wasConfirmed());
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasConfirmed()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasConfirmedFlownOnceConfirmed() throws PassengerException {
		//create a passenger currently in queue
		Business p = createPassengerFlown();
		assertTrue(p.wasConfirmed());
	}
	
	

	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#wasQueued()
	//****************************************************************************************
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasQueuedPassengerNewAndNeverQueued() throws PassengerException {
		Business p = new Business(10,40);
		assertTrue(!p.wasQueued());
	}

	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasQueuedPassengerNewOnceQueued() throws PassengerException {
		Business p = createPassengerInQueue();
		p.confirmSeat(10, 40);
		p.cancelSeat(30);
		assertTrue(p.wasQueued());
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasQueuedPassengerCurrentlyQueued() throws PassengerException {
		//create a passenger currently in queue
		Business p = createPassengerInQueue();
		assertTrue(p.wasQueued());
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasQueuedPassengerCurrentlyConfirmedNeverInQueue() throws PassengerException {
		//create a passenger currently in queue
		Business p = createPassengerConfirmed();
		assertTrue(!p.wasQueued());
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasQueuedPassengerCurrentlyConfirmedOnceInQueue() throws PassengerException {
		//create a passenger currently in queue
		Business p = createPassengerInQueue();
		p.confirmSeat(10, 15);
		assertTrue(p.wasQueued());
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasQueuedRefusedNeverInQueue() throws PassengerException {
		//create a passenger currently in queue
		Business p = createPassengerRefused();
		assertTrue(!p.wasQueued());
	}
	
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasQueuedRefusedOnceInQueue() throws PassengerException {
		//create a passenger currently in queue
		Business p = createPassengerInQueue();
		p.refusePassenger(20);
		assertTrue(p.wasQueued());
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasQueuedFlownNeverInQueue() throws PassengerException {
		//create a passenger currently in queue
		Business p = createPassengerFlown();
		assertTrue(!p.wasQueued());
	}
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#wasQueued()}.
	 * @throws PassengerException 
	 */
	@Test
	public void testWasQueuedFlownOnceInQueue() throws PassengerException {
		//create a passenger currently in queue
		Business p = createPassengerInQueue();
		p.confirmSeat(10, 30);
		p.flyPassenger(20);
		assertTrue(p.wasQueued());
	}
	
	
	//****************************************************************************************
	// Test methods for asgn2Passengers.Passenger#copyPassengerState()
	//****************************************************************************************
	
	/**
	 * Test method for {@link asgn2Passengers.Passenger#copyPassengerState(asgn2Passengers.Passenger)}.
	 */
	@Test
	public void testCopyPassengerStateDontTest() {
		//tested through upgrade functions in other files
		assertTrue(true);
	}

//********************************************************************************************
//********************************************************************************************
// 	Private helper methods
//********************************************************************************************
//********************************************************************************************
		
	
	private Business createPassengerInQueue() throws PassengerException{
		//create a passenger currently in queue
				Business p = new Business(10,40);
				p.queuePassenger(10, 15);
				return p;
	}
	
	private Business createPassengerConfirmed() throws PassengerException{
		//create a passenger currently confirmed
				Business p = new Business(10,40);
				p.confirmSeat(10,20);
				return p;
	}
	
	
	private Business createPassengerRefused() throws PassengerException{
		//create a passenger currently confirmed
				Business p = new Business(10,40);
				p.refusePassenger(13);
				return p;
	}
	
	
	private Business createPassengerFlown() throws PassengerException{
		//create a passenger currently confirmed
				Business p = new Business(10,40);
				p.confirmSeat(10,20);
				p.flyPassenger(20);
				return p;
	}
}
