
public class Transaction_2017336_2017110 {
	int id;
	public Transaction_2017336_2017110(int tid) {
		this.id=tid;
	}
	public void run() {
		switch(this.id) {
		case 0:
			this.reserve();
			break;
		case 1:
			this.cancel();
			break;
		case 2:
			this.my_flights();
			break;
		case 3:
			this.total_reservations();
			break;
		case 4:
			this.transfer();
			break;
		}
	}
	
	public void reserve() {
		
	}
	public void cancel() {
		
	}
	public void my_flights() {
		
	}
	public int total_reservations() {
		return Database_2017336_2017110.totalReservations;
	}
	public void transfer() {
		
	}
}
