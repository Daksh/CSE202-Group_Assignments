
public final class CCM_2017336_2017110 {
	private static int databaseLock;
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
	
	public static synchronized void getSharedLock() {
		assert databaseLock>=0;
		Thread currentThread = Thread.currentThread();
	    System.out.println("Thread #" + currentThread.getId()+" doing getSharedLock(), currently, lock:"+databaseLock);   
		
		while(databaseLock==1) { //busy wait, only when there is an X lock
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if(databaseLock==0)
			databaseLock=2;
		else//note, the database lock cannot be 1 here
			databaseLock++;
		
		System.out.println("Thread #" + currentThread.getId()+" got SharedLock, currently, lock:"+databaseLock);
	}
	
	public static synchronized void getExclusiveLock() {
		assert databaseLock>=0;
		Thread currentThread = Thread.currentThread();
	    System.out.println("Thread #" + currentThread.getId()+" doing getXLock(), currently, lock:"+databaseLock);
		
		while(databaseLock!=0) { //busy wait, when lock is NOT unlocked
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		databaseLock=1;//only when databaseLock was 0
		
		System.out.println("Thread #" + currentThread.getId()+" got XLock, currently, lock:"+databaseLock);
	}

	public static synchronized void unLockShared() {
		assert databaseLock>=2;		
		Thread currentThread = Thread.currentThread();
	    System.out.println("Thread #" + currentThread.getId()+" doing unLockS(), currently, lock:"+databaseLock);
	    
	    if(databaseLock==2)
	    	databaseLock = 0;
	    else
	    	databaseLock--;
	    
	    System.out.println("Thread #" + currentThread.getId()+" got UnlockedS, currently, lock:"+databaseLock);
	}
	public static void unLockExclusive() {
	    assert databaseLock==1;
	    Thread currentThread = Thread.currentThread();
	    System.out.println("Thread #" + currentThread.getId()+" doing unLockX(), currently, lock:"+databaseLock);
	    
	    databaseLock = 0;
	    
	    System.out.println("Thread #" + currentThread.getId()+" got UnlockedX, currently, lock:"+databaseLock);
	}

}
