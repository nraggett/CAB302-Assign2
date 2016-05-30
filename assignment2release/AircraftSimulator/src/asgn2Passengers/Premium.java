/**
 * 
 */
package asgn2Passengers;

/**
 * @author hogan
 *
 */
public class Premium extends Passenger {

	/**
	 * Premium Constructor (Partially Supplied)
	 * Passenger is created in New state, later given a Confirmed Premium Class reservation, 
	 * Queued, or Refused booking if waiting list is full. 
	 * 
	 * @param bookingTime <code>int</code> day of the original booking. 
	 * @param departureTime <code>int</code> day of the intended flight.  
	 * @throws PassengerException if invalid bookingTime or departureTime 
	 * @see asgnPassengers.Passenger#Passenger(int,int)
	 */
	public Premium(int bookingTime,int departureTime) throws PassengerException {
		super(bookingTime, departureTime);
		this.passID = "P:" + this.passID;
	}
	
	/**
	 * Simple constructor to support {@link asgn2Passengers.Passenger#upgrade()} in other subclasses
	 */
	protected Premium() {
		
	}

	@Override
	public Passenger upgrade() {
		//business upgrades to first.
		
		 Business test = null;
		try {
			test = new Business(this.bookingTime,this.departureTime);
		} catch (PassengerException e) {
			e.printStackTrace();
		}
		 test.copyPassengerState(this);
		 test.confirmationTime = this.confirmationTime;
		 
		//change pass id
		String newPassID = 'F'+passID.substring(1,(this.getPassID().length()-1));
		test.passID = newPassID;
		return test; 
	}
	
	@Override
	public String noSeatsMsg() {
		return "No seats available in Premium";
	}
}
