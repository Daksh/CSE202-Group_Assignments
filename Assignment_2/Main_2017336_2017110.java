/*
 * Does pre-processing,
 * 	Add Passengers, Flights
 */
public class Main_2017336_2017110{
	public final static int NUM_FLIGHTS = 5;
	public final static int NUM_PASSENGERS = 60;
	private final static int NUM_THREADS = 6;
	
    public static void main(String args[]){
    	CCM_2017336_2017110.initLock();
    	
    	for(int i=1; i<=NUM_FLIGHTS; i++) {
    		Flight_2017336_2017110 f = new Flight_2017336_2017110(i);
    		Database_2017336_2017110.addFlight(i,f);
    	}
    	for(int i=1; i<=NUM_PASSENGERS; i++) {
    		Passenger_2017336_2017110 p = new Passenger_2017336_2017110(i);
    		Database_2017336_2017110.addPassenger(i,p);
    	}
    	
    	for(int i=1; i<=NUM_THREADS; i++) {
    		Transaction_Thread_2017336_2017110 tt = new Transaction_Thread_2017336_2017110();
    		tt.start();
    	}
    	
    }
}
