import java.util.ArrayList;

public class Passenger_2017336_2017110 {
	int id;
	ArrayList<Flight_2017336_2017110> myFlights = new ArrayList<Flight_2017336_2017110>();
	
	public Passenger_2017336_2017110(int i) {
		this.id = i;
	}
	
	public void addFlight(Flight_2017336_2017110 f) {
		this.myFlights.add(f);
	}
}
