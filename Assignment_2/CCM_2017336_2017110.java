
public final class CCM_2017336_2017110 {
	private static boolean databaseLock;
	
	public static void initLock() {
		databaseLock = false;
	}
	
	public static synchronized void getLock() {
		while(databaseLock==true) { //busy wait
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		databaseLock=true;
	}

	public static void unLock() {
	    if(databaseLock==false) assert 1==0;		
	    databaseLock = false;
	}

}
