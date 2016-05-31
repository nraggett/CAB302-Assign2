/**
 * 
 */
package asgn2Passengers;

/**
 * @author hogan
 *
 */
public class Economy extends Passenger {

	/**
	 * Economy Constructor (Partially Supplied)
	 * Passenger is created in New state, later given a Confirmed Economy Class reservation, 
	 * Queued, or Refused booking if waiting list is full. 
	 * 
	 * @param bookingTime <code>int</code> day of the original booking. 
	 * @param departureTime <code>int</code> day of the intended flight.  
	 * @throws PassengerException if invalid bookingTime or departureTime 
	 * @see asgnPassengers.Passenger#Passenger(int,int)
	 */
	public Economy(int bookingTime,int departureTime) throws PassengerException {
		super(bookingTime, departureTime);
		this.passID = "Y:" + this.passID;
	}
	
	@Override
	public String noSeatsMsg() {
		return "No seats available in Economy";
	}

	@Override
	public Passenger upgrade() {
		//Economy upgrades to Premium.
		
		 Premium test = null;
		try {
			test = new Premium(this.bookingTime,this.departureTime);
		} catch (PassengerException e) {
			e.printStackTrace();
		}
		 test.copyPassengerState(this);
		 test.confirmationTime = this.confirmationTime;
		 
		//change pass id
		//String newPassID = 'Y'+passID.substring(1,(this.getPassID().length()-1));
		//test.passID = newPassID;
		return test; 
	}
}
