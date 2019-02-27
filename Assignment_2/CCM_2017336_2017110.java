
public final class CCM_2017336_2017110 {
	private static int databaseLock;
	
	private static final SyncLock_2017336_2017110 SLOCK = new SyncLock_2017336_2017110();
	private static final SyncLock_2017336_2017110 SLOCK_2 = new SyncLock_2017336_2017110();
	private static final SyncLock_2017336_2017110 XLOCK = new SyncLock_2017336_2017110();
	
	/*
	 * 0 -> Unlocked
	 * 1 -> Exclusive Lock (X)
	 * 2+ -> Shared Lock (S) 
	 * where 2 denotes, exactly 1 thread has the shared lock
	 * 3 denotes, 2 threads have the shared lock, and so on... 
	 */
	
	public static void initLock() {
		databaseLock = 0;
	}
	
	private static void sleepMinimal() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void getSharedLock() {
		synchronized(SLOCK) {
			assert databaseLock>=0;
			Thread currentThread = Thread.currentThread();
			if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing getSharedLock(), currently, lock:"+databaseLock);   
			
			while(databaseLock==1) { //busy wait, only when there is an X lock
				sleepMinimal();
			}
			
			if(databaseLock==0)
				databaseLock=2;
			else//note, the database lock cannot be 1 here
				databaseLock++;
			
			if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" got SharedLock, currently, lock:"+databaseLock);
		}
	}
	
	public static void getExclusiveLock() {
		synchronized(XLOCK) {
			assert databaseLock>=0;
			Thread currentThread = Thread.currentThread();
			if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing getXLock(), currently, lock:"+databaseLock);
			
			while(databaseLock!=0) { //busy wait, when lock is NOT unlocked
				sleepMinimal();
			}
			
			databaseLock=1;//only when databaseLock was 0
			
			if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" got XLock, currently, lock:"+databaseLock);
		}
	}

	public static void unLockShared() {
		synchronized (SLOCK_2) {
			assert databaseLock>=2;		
			Thread currentThread = Thread.currentThread();
			if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing unLockS(), currently, lock:"+databaseLock);
			sleepMinimal();
		    if(databaseLock==2)
		    	databaseLock = 0;
		    else
		    	databaseLock--;
		    
		    if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" got UnlockedS, currently, lock:"+databaseLock);
		}
	}
	public static void unLockExclusive() {
		synchronized (SLOCK_2) {
		    assert databaseLock==1;
		    Thread currentThread = Thread.currentThread();
		    if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing unLockX(), currently, lock:"+databaseLock);
		    sleepMinimal();
		    databaseLock = 0;
		    
		    if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" got UnlockedX, currently, lock:"+databaseLock);
		}
	}

}
