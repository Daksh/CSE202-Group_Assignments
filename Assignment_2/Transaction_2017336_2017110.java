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

		Thread currentThread = Thread.currentThread();
	    System.out.println("Thread #" + currentThread.getId()+" will perform reserve");

		
		Passenger_2017336_2017110 passenger = Database_2017336_2017110.getPassenger(pid);
		Database_2017336_2017110.getFlight(fid).addPassenger(passenger);
		CCM_2017336_2017110.unLock();
	}
	
	/*
	 * Cancel(F, i): cancel reservation for passenger with id i from flight F.
	 */
	public void cancel(int fid, int pid) {
		CCM_2017336_2017110.getLock();
		
		Thread currentThread = Thread.currentThread();
	    System.out.println("Thread #" + currentThread.getId()+" will perform cancel");
		
		//remove flight-> passenger
		Database_2017336_2017110.getFlight(fid).removePassenger(Database_2017336_2017110.getPassenger(pid));
		
		//remove passenger -> flight
		Database_2017336_2017110.getPassenger(pid).removeFlight(Database_2017336_2017110.getFlight(fid));
		
		//reduce db -> totalReservations
		Database_2017336_2017110.reduceTotalReservations();
		
		CCM_2017336_2017110.unLock();
	}
	
	/*
	 * My_Flights(id): returns the set of flights on which passenger i has a reservation.
	 */
	public String my_flights(int pid) {
		CCM_2017336_2017110.getLock();
		
		Thread currentThread = Thread.currentThread();
	    System.out.println("Thread #" + currentThread.getId()+" will perform my_flights");

		
		String r = Database_2017336_2017110.getPassenger(pid).getAllFlights();
		
		CCM_2017336_2017110.unLock();
		
		return r;
	}
	
	/*
	 * Total_Reservations(): returns the sum total of all reservations on all flights.
	 */
	public int total_reservations() {
		CCM_2017336_2017110.getLock();
		
		Thread currentThread = Thread.currentThread();
	    System.out.println("Thread #" + currentThread.getId()+" will perform total_reservations");
		
	    int val = Database_2017336_2017110.getTotalReservations();
		CCM_2017336_2017110.unLock();
		return val;
	}
	
	/*
	 * Transfer(F1,F2,i): transfer passenger i from flight F1 to F2. This transaction should have no impact if the passenger is not found in F1 or there is no room in F2.
	 */
	public void transfer(int fid_from, int fid_to, int pid) {
		CCM_2017336_2017110.getLock();
		
		Thread currentThread = Thread.currentThread();
	    System.out.println("Thread #" + currentThread.getId()+" will perform transfer");
		
		boolean check = Database_2017336_2017110.getFlight(fid_to).possibleAddition() && Database_2017336_2017110.getFlight(fid_from).has(pid); 
		CCM_2017336_2017110.unLock();
		
		if(check) {
			this.cancel(fid_from,pid);
			this.reserve(fid_to, pid);
		}
	}
}
