import java.util.ArrayList;

public final class CCM_2017336_2017110 {
	private static boolean databaseLock;
		
	private static final SyncLock_2017336_2017110 LOCK_Obj = new SyncLock_2017336_2017110();
	
	public static void initLock() {
		databaseLock = false;
	}
	
	public static synchronized void getLock() throws InterruptedException {
		Thread currentThread = Thread.currentThread();
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing getSharedLock(), currently, lock:"+databaseLock);   
		
//		if(databaseLock==1) {
//			sWaiting.add(currentThread);//at the end
//			currentThread.wait();
//		}
		while(databaseLock==true) { //busy wait
			Thread.sleep(1);
		}
		
		databaseLock = true;
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" got SharedLock, currently, lock:"+databaseLock);	
	}
	

	public static void releaseLock() throws InterruptedException {
		assert databaseLock==true;
		Thread currentThread = Thread.currentThread();
		if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing unLockS(), currently, lock:"+databaseLock);
		
		databaseLock=false;
	    
	    if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" got UnlockedS, currently, lock:"+databaseLock);
	}

}
