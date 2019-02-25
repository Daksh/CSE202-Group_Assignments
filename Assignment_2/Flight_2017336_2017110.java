import java.util.ArrayList;

public class Flight_2017336_2017110 {
	int id;//maybe
	ArrayList<Passenger_2017336_2017110> passengersList = new ArrayList<Passenger_2017336_2017110>();
	int capacity;
	
	public Flight_2017336_2017110(int i) {
		this.id = i;
	}
	
	public void addPassenger(Passenger_2017336_2017110 p) {
		if(this.passengersList.size()<this.capacity) {
			this.passengersList.add(p);
			p.addFlight(this);
		}
	}
	
}
