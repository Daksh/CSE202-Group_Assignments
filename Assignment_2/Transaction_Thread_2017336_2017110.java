import java.util.Random;

/*

Each transaction thread (TT) should have the following structure:

Repeat
------
Select a transaction type randomly
Select object (flight and passenger id) for transaction randomly Invoke transaction
The possible transactions are:
 * Reserve(F, i): reserve a seat for passenger with id i on flight F, where i > 0.
 * Cancel(F, i): cancel reservation for passenger with id i from flight F.
 * My_Flights(id): returns the set of flights on which passenger i has a reservation.
 * Total_Reservations(): returns the sum total of all reservations on all flights.
 * Transfer(F1,F2,i): transfer passenger i from flight F1 to F2. This transaction should have no impact if the passenger is not found in F1 or there is no room in F2. 
*/
public class Transaction_Thread_2017336_2017110 extends Thread{
	private static final int REPEAT_COUNT = 100; 
	
	Random rand = new Random();
	
	public void executeARandomTxn() throws InterruptedException {
		Transaction_2017336_2017110 txn;
		
		int n = rand.nextInt(5); // n in range [0,4]
//		if(n>4)
//			txn = new Transaction_2017336_2017110(0);
		txn = new Transaction_2017336_2017110(n);
		txn.run();
	}
	
	@Override
	public void run() {
		if(Main_2017336_2017110.printDesc)
			System.out.println("Running a thread");
		for(int i=0; i<REPEAT_COUNT; i++)
			try {
				this.executeARandomTxn();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	

}
