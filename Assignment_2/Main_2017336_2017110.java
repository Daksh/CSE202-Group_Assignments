/*
 * Does pre-processing,
 * 	Add Passengers, Flights
 */
public class Main_2017336_2017110{
	public final static int NUM_FLIGHTS = 5;
	public final static int NUM_PASSENGERS = 60;
	public final static boolean printDesc = true;
	public final static boolean printDebug = false;
	
	private static int NUM_THREADS = 6;
	
    public static void main(String args[]){
//    	for(NUM_THREADS=1;NUM_THREADS<21; NUM_THREADS++) {
    	for(NUM_THREADS=6;NUM_THREADS==6; NUM_THREADS++) {
    		CCM_2017336_2017110.initLock();
        	Transaction_Thread_2017336_2017110[] threadArray = new Transaction_Thread_2017336_2017110[NUM_THREADS];
        	long startTime = System.nanoTime();

        	for(int i=1; i<=NUM_FLIGHTS; i++) {
        		Flight_2017336_2017110 f = new Flight_2017336_2017110(i);
        		Database_2017336_2017110.addFlight(i,f);
        	}
        	for(int i=1; i<=NUM_PASSENGERS; i++) {
        		Passenger_2017336_2017110 p = new Passenger_2017336_2017110(i);
        		Database_2017336_2017110.addPassenger(i,p);
        	}
        	
        	for(int i=0; i<NUM_THREADS; i++) {
        		threadArray[i] = new Transaction_Thread_2017336_2017110();
        		threadArray[i].start();
        	}
        	
        	//Wait for all the threads to return from their executions
        	for(int i=0; i<NUM_THREADS; i++) {
        		try {
    				threadArray[i].join();
    			} catch (InterruptedException e1) {
    				e1.printStackTrace();
    			}
        	}
        	
        	long endTime = System.nanoTime();
        	long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.
        	
        	//Throughput is the number of transactions per second
        	System.out.println("Took: "+duration/1000000+"ms for "+NUM_THREADS+" threads: Throughput: "+(100f*NUM_THREADS)/(duration/1000000000f));
    	}	
    }
}
