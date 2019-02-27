import java.util.HashMap;

public final class CCM_2017336_2017110 {
	volatile private static HashMap<Integer, Boolean> flightLockTable = new HashMap<Integer, Boolean>();
	volatile private static HashMap<Integer, Boolean> passengerLockTable = new HashMap<Integer, Boolean>();
	volatile private static boolean trLock;
	
	public static void initLock() {
		for(int i=1; i<=Main_2017336_2017110.NUM_FLIGHTS; i++)
			flightLockTable.put(i, false);
		for(int i=1; i<=Main_2017336_2017110.NUM_PASSENGERS; i++)
			passengerLockTable.put(i, false);
		trLock = false;
	}
	
	public static synchronized void getTRLock() throws InterruptedException {
		Thread currentThread = Thread.currentThread();
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing getTRLock(), currently, lock:"+trLock);   
		
		while(trLock == true) { //busy wait
			Thread.sleep(1);
//			System.out.println("wait tr");
		}
		trLock = true;
		
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" got getTRLock(), currently, lock:"+trLock);
	}	

	public static void releaseTRLockLock() {
		assert trLock==true;
		
		Thread currentThread = Thread.currentThread();
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing releaseTRLockLock(), currently, lock:"+trLock);
		trLock = false;
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" releaseTRLockLock() completed, currently, lock:"+trLock);
	}
	
	
	public static synchronized void getFlightLock(int flight_id) throws InterruptedException {
		Thread currentThread = Thread.currentThread();
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing getFlightLock("+flight_id+"), currently, lock:"+flightLockTable.get(flight_id));
		
		while(flightLockTable.get(flight_id) == true) { //busy wait
			Thread.sleep(1);
//			System.out.println("wait fl");
		}
		flightLockTable.put(flight_id, true);
		
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" done getFlightLock("+flight_id+"), currently, lock:"+flightLockTable.get(flight_id));
	}	

	public static void releaseFlightLock(int flight_id) throws InterruptedException {
		assert flightLockTable.get(flight_id)==true;
		
		Thread currentThread = Thread.currentThread();
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing releaseFlightLock("+flight_id+"), currently, lock:"+flightLockTable.get(flight_id));
		flightLockTable.put(flight_id, false);
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" done releaseFlightLock("+flight_id+"), currently, lock:"+flightLockTable.get(flight_id));
	}
	
	public static synchronized void getPassengerLock(int passenger_id) throws InterruptedException {
		Thread currentThread = Thread.currentThread();
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing getPassengerLock("+passenger_id+"), currently, lock:"+passengerLockTable.get(passenger_id));
		while(passengerLockTable.get(passenger_id) == true) { //busy wait
			Thread.sleep(1);
//			System.out.println("wait pl");
		}
		passengerLockTable.put(passenger_id, true);
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" done getPassengerLock("+passenger_id+"), currently, lock:"+passengerLockTable.get(passenger_id));
	}	

	public static void releasePassengerLock(int passenger_id) throws InterruptedException {
		assert passengerLockTable.get(passenger_id)==true;
		Thread currentThread = Thread.currentThread();
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing releasePassengerLock("+passenger_id+"), currently, lock:"+passengerLockTable.get(passenger_id));
		passengerLockTable.put(passenger_id, false);
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" done releasePassengerLock("+passenger_id+"), currently, lock:"+passengerLockTable.get(passenger_id));
	}

}
