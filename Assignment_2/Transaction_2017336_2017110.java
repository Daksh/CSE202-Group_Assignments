import java.util.Random;

public class Transaction_2017336_2017110 {
	int id;
	Random rand = new Random();
	
	public Transaction_2017336_2017110(int tid) {
		this.id=tid;
	}
	public void run() {
		//Generate Random Values, which are passed to the functions below
		int fid = 1+rand.nextInt(Main_2017336_2017110.NUM_FLIGHTS);
		int fid2 = 1+rand.nextInt(Main_2017336_2017110.NUM_FLIGHTS);
		int pid = 1+rand.nextInt(Main_2017336_2017110.NUM_PASSENGERS);
		
		switch(this.id) {
		case 0:
			this.reserve(fid,pid);
			break;
		case 1:
			this.cancel(fid,pid);
			break;
		case 2:
			System.out.println("Flights List of Passenger #"+pid+": "+this.my_flights(pid));
			break;
		case 3:
			int t = this.total_reservations();
			System.out.println("Total Number of Reservations: "+t);
			break;
		case 4:
			this.transfer(fid,fid2,pid);
			break;
		}
	}
	
	/*
	 * Reserve(F, i): reserve a seat for passenger with id i on flight F, where i > 0.
	 */
	public void reserve(int fid, int pid) {
		CCM_2017336_2017110.getLock();
		
		Passenger_2017336_2017110 passenger = Database_2017336_2017110.getPassenger(pid);
		Flight_2017336_2017110 flight = Database_2017336_2017110.getFlight(fid);
		assert fid == flight.id;
		assert passenger.id == pid;
		
		System.out.println("Reserving Flight #"+fid+" for Passenger #"+pid);
		
		if(!reservationExists(passenger, flight) && flight.possibleAddition()) {
			flight.addPassenger(passenger);
			passenger.addFlight(flight);
			Database_2017336_2017110.increaseTotalReservations();
		}

		CCM_2017336_2017110.unLock();
	}
	
	private boolean reservationExists(Passenger_2017336_2017110 passenger, Flight_2017336_2017110 flight) {
		//MUST BE CALLED WITH A LOCK
		if(passenger.checkFlight(flight) && flight.checkPassenger(passenger)) {
			return true;
		} else if(!passenger.checkFlight(flight) && !flight.checkPassenger(passenger)) {
			return false;
		} 
		assert 2==3;
		return false;
	}
	
	/*
	 * Cancel(F, i): cancel reservation for passenger with id i from flight F.
	 */
	public void cancel(int fid, int pid) {
		CCM_2017336_2017110.getLock();
		
		Flight_2017336_2017110 flight = Database_2017336_2017110.getFlight(fid);
		Passenger_2017336_2017110 passenger = Database_2017336_2017110.getPassenger(pid);
		
		if(reservationExists(passenger, flight)) {
			//remove flight-> passenger
			flight.removePassenger(Database_2017336_2017110.getPassenger(pid));
			
			//remove passenger -> flight
			passenger.removeFlight(Database_2017336_2017110.getFlight(fid));
			
			//reduce db -> totalReservations
			Database_2017336_2017110.reduceTotalReservations();
		}
		
		CCM_2017336_2017110.unLock();
	}
	
	/*
	 * My_Flights(id): returns the set of flights on which passenger i has a reservation.
	 */
	public String my_flights(int pid) {
		CCM_2017336_2017110.getLock();
		
		String r = Database_2017336_2017110.getPassenger(pid).getAllFlights();
		
		CCM_2017336_2017110.unLock();

		return r;
	}
	
	/*
	 * Total_Reservations(): returns the sum total of all reservations on all flights.
	 */
	public int total_reservations() {
		CCM_2017336_2017110.getLock();
		
	    int val = Database_2017336_2017110.getTotalReservations();
		CCM_2017336_2017110.unLock();
		return val;
	}
	
	/*
	 * Transfer(F1,F2,i): transfer passenger i from flight F1 to F2. This transaction should have no impact if the passenger is not found in F1 or there is no room in F2.
	 */
	public void transfer(int fid_from, int fid_to, int pid) {
		CCM_2017336_2017110.getLock();
		
		boolean check = Database_2017336_2017110.getFlight(fid_to).possibleAddition() && Database_2017336_2017110.getFlight(fid_from).has(pid); 
		CCM_2017336_2017110.unLock();
		
		if(check) {
			this.cancel(fid_from,pid);
			this.reserve(fid_to, pid);
		}
	}
}
