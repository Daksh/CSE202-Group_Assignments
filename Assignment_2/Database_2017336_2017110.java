
/*
 * The database has a set of transactions and data structures 
 * containing flight information (passenger lists for all flights).
*/
public class Database_2017336_2017110 {
	private static final int SLEEP_DUR = 10;
	
	private static int totalReservations;
	private static Flight_2017336_2017110 flights[] = new Flight_2017336_2017110[Main_2017336_2017110.NUM_FLIGHTS+1];
	private static Passenger_2017336_2017110 passengers[] = new Passenger_2017336_2017110[Main_2017336_2017110.NUM_PASSENGERS+1];
	
	private static boolean lock=false;
	
	private static void sleep() {
		try {
			Thread.sleep(SLEEP_DUR);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int getTotalReservations() {
		sleep();
		return totalReservations;
	}
	public static void reduceTotalReservations() {
		sleep();
		totalReservations-=1;
	}
	
	public static Flight_2017336_2017110 getFlight(int id) {
		sleep();
		return flights[id];
	}
	
	public static Passenger_2017336_2017110 getPassenger(int id) {
		sleep();
		return passengers[id];
	}
	
	public static void addFlight(int id, Flight_2017336_2017110 f) {
		sleep();
		flights[id]=f;
	}

	public static void addPassenger(int id, Passenger_2017336_2017110 p) {
		sleep();
		passengers[id]=p;
	}

	public static void getLock() {
		while(lock==true);//busy wait
		lock=true;
	}

	public static void unLock() {
		if(lock==false) assert 1==0;
		lock=false;
	}
}