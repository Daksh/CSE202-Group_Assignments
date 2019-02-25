
public class Transaction_2017336_2017110 {
	int id;
	public Transaction_2017336_2017110(int tid) {
		this.id=tid;
	}
	public void run() {
		//Generate Random Values, which are passed to the functions below
		
		switch(this.id) {
		case 0:
//			this.reserve();
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
	
	/*
	 * Reserve(F, i): reserve a seat for passenger with id i on flight F, where i > 0.
	 */
	public void reserve(int fid, int pid) {
		Passenger_2017336_2017110 passenger = Database_2017336_2017110.getPassenger(pid);
		Database_2017336_2017110.getFlight(fid).addPassenger(passenger);
	}
	public void cancel() {
		
	}
	public void my_flights() {
		
	}
	public int total_reservations() {
		return Database_2017336_2017110.getTotalReservations();
	}
	public void transfer() {
		
	}
}
