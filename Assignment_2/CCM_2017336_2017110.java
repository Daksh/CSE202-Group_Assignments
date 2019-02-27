public final class CCM_2017336_2017110 {
	private static boolean databaseLock;
	
	public static void initLock() {
		databaseLock = false;
	}
	
	public static synchronized void getLock() throws InterruptedException {
		Thread currentThread = Thread.currentThread();
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing getLock(), currently, lock:"+databaseLock);   
		
		while(databaseLock==true) { //busy wait
			Thread.sleep(1);
		}
		
		databaseLock = true;
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" got lock, currently, lock:"+databaseLock);	
	}
	

	public static void releaseLock() throws InterruptedException {
		assert databaseLock==true;
		Thread currentThread = Thread.currentThread();
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing releaseLock(), currently, lock:"+databaseLock);
		
		databaseLock=false;
	    
	    if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" released lock, currently, lock:"+databaseLock);
	}

}
