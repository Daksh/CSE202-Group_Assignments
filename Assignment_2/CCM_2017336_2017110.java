import java.util.ArrayList;

public final class CCM_2017336_2017110 {
	private static int databaseLock;
	/*
	 * 0 -> Unlocked
	 * 1 -> Exclusive Lock (X)
	 * 2+ -> Shared Lock (S) 
	 * where 2 denotes, exactly 1 thread has the shared lock
	 * 3 denotes, 2 threads have the shared lock, and so on... 
	 */
	
	private static ArrayList<Thread> xWaiting = new ArrayList<Thread>();
	private static ArrayList<Thread> sWaiting = new ArrayList<Thread>();
	
	private static final SyncLock_2017336_2017110 LOCK_Obj = new SyncLock_2017336_2017110();
	
	public static void initLock() {
		databaseLock = 0;
	}
	
	public static void getSharedLock() throws InterruptedException {
		synchronized(LOCK_Obj) {
			assert databaseLock>=0;
			Thread currentThread = Thread.currentThread();
			if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing getSharedLock(), currently, lock:"+databaseLock);   
			
			if(databaseLock==1) {
				sWaiting.add(currentThread);//at the end
				currentThread.wait();
			}
//			while(databaseLock==1) { //busy wait, only when there is an X lock
//				Thread.sleep(10);
//			}
			
			if(databaseLock==0)
				databaseLock=2;
			else//note, the database lock cannot be 1 here
				databaseLock++;
			
			if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" got SharedLock, currently, lock:"+databaseLock);
		}
	}
	
	public static void getExclusiveLock() throws InterruptedException {
		synchronized(LOCK_Obj) {
			assert databaseLock>=0;
			Thread currentThread = Thread.currentThread();
			if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing getXLock(), currently, lock:"+databaseLock);
			
			if(databaseLock!=0) {
				xWaiting.add(currentThread);
				currentThread.wait();
			}
//			while(databaseLock!=0) { //busy wait, when lock is NOT unlocked
//				Thread.sleep(10);
//			}
			
			databaseLock=1;//only when databaseLock was 0
			
			if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" got XLock, currently, lock:"+databaseLock);
		}
	}

	public static void unLockShared() throws InterruptedException {
		synchronized (LOCK_Obj) {
			assert databaseLock>=2;		
			Thread currentThread = Thread.currentThread();
			if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing unLockS(), currently, lock:"+databaseLock);
			Thread.sleep(10);
		    if(databaseLock==2)
		    	databaseLock = 0;
		    else
		    	databaseLock--;
		    
		    if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" got UnlockedS, currently, lock:"+databaseLock);
		}
	}
	public static void unLockExclusive() throws InterruptedException {
		synchronized (LOCK_Obj) {
		    assert databaseLock==1;
		    Thread currentThread = Thread.currentThread();
		    if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" doing unLockX(), currently, lock:"+databaseLock);
		    Thread.sleep(10);
		    databaseLock = 0;
		    
		    if(Main_2017336_2017110.printDebug) System.out.println("Thread #" + currentThread.getId()+" got UnlockedX, currently, lock:"+databaseLock);
		}
	}

}
