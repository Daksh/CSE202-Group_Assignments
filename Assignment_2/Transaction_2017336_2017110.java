import java.util.Random;

public class Transaction_2017336_2017110 {
	int id;
	Random rand = new Random();
	
	public Transaction_2017336_2017110(int tid) {
		this.id=tid;
	}
	public void run() throws InterruptedException {
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
			if(Main_2017336_2017110.printDesc)
				System.out.println("Flights List of Passenger #"+pid+": "+this.my_flights(pid));
			break;
		case 3:
			int t = this.total_reservations();
			if(Main_2017336_2017110.printDesc)
				System.out.println("Total Number of Reservations: "+t);
			break;
		case 4:
			this.transfer(fid,fid2,pid);
			break;
		}
	}
	
	/*
	 * Reserve(F, i): reserve a seat for passenger with id i on flight F, where i > 0.
	 * 
	 * Needs to write on a particular flight, particular passenger and totalReservations
	 */
	public void reserve(int fid, int pid) throws InterruptedException {
		CCM_2017336_2017110.getLock();
		
		Passenger_2017336_2017110 passenger = Database_2017336_2017110.getPassenger(pid);
		Flight_2017336_2017110 flight = Database_2017336_2017110.getFlight(fid);
		assert fid == flight.id;
		assert passenger.id == pid;
		
		if(Main_2017336_2017110.printDesc)
			System.out.println("Reserving Flight #"+fid+" for Passenger #"+pid);
		
		if(!reservationExists(passenger, flight) && flight.possibleAddition()) {
			flight.addPassenger(passenger.id);
			passenger.addFlight(flight);
			Database_2017336_2017110.increaseTotalReservations();
		}

		CCM_2017336_2017110.releaseLock();
	}
	
	/*
	/* 	MUST BE CALLED WITH AN EXISTING LOCK (S)
	 *  Needs, Read access on a particular passenger, particular flight
	 */
	private boolean reservationExists(Passenger_2017336_2017110 passenger, Flight_2017336_2017110 flight) {
		if(passenger.checkFlight(flight.id) && flight.checkPassenger(passenger.id))
			return true;
		else if(!passenger.checkFlight(flight.id) && !flight.checkPassenger(passenger.id))
			return false;
		assert 2==3;
		return false;
	}
	
	/*
	 * Cancel(F, i): cancel reservation for passenger with id i from flight F.
	 * Needs to write on a particular flight, particular passenger and totalReservations
	 */
	public void cancel(int fid, int pid) throws InterruptedException {
		CCM_2017336_2017110.getLock();
		
		Flight_2017336_2017110 flight = Database_2017336_2017110.getFlight(fid);
		Passenger_2017336_2017110 passenger = Database_2017336_2017110.getPassenger(pid);
		
		if(reservationExists(passenger, flight)) {
			//remove flight-> passenger
			assert Database_2017336_2017110.getPassenger(pid).id==pid;
			flight.removePassenger(pid);
			
			//remove passenger -> flight
			assert Database_2017336_2017110.getFlight(fid).id==fid;
			passenger.removeFlight(fid);
			
			//reduce db -> totalReservations
			Database_2017336_2017110.reduceTotalReservations();
		}
		
		CCM_2017336_2017110.releaseLock();
	}
	
	/*
	 * My_Flights(id): returns the set of flights on which passenger i has a reservation.
	 * Needs Read Access on {a set of flights, and a passenger} 
	 */
	public String my_flights(int pid) throws InterruptedException {
		CCM_2017336_2017110.getLock();
		String r = Database_2017336_2017110.getPassenger(pid).getAllFlights();
		CCM_2017336_2017110.releaseLock();

		return r;
	}
	
	/*
	 * Total_Reservations(): returns the sum total of all reservations on all flights.
	 * Needs Read Access only on the variable TotalReservations
	 */
	public int total_reservations() throws InterruptedException {
		CCM_2017336_2017110.getLock();
	    int val = Database_2017336_2017110.getTotalReservations();
		CCM_2017336_2017110.releaseLock();

		return val;
	}
	
	/*
	 * Transfer(F1,F2,i): transfer passenger i from flight F1 to F2. 
	 * This transaction should have no impact if the passenger is not 
	 * found in F1 or there is no room in F2.
	 * 
	 * Some locks are taken in the functions called. 
	 * The part done here, needs read lock 
	 * 1. flight fid_to
	 * 2. flight fid_from
	 */
	public void transfer(int fid_from, int fid_to, int pid) throws InterruptedException {
		CCM_2017336_2017110.getLock();
		boolean check = Database_2017336_2017110.getFlight(fid_to).possibleAddition() && Database_2017336_2017110.getFlight(fid_from).has(pid); 
		CCM_2017336_2017110.releaseLock();
		
		if(check) {
			this.cancel(fid_from,pid);
			this.reserve(fid_to, pid);
		}
	}
}
