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

	public String getAllFlights() {
		String ret = "";
		for(int i = 0; i<this.myFlights.size(); i++)
			ret+=this.myFlights.get(i).id+", ";
		if(ret.length()>2)
			return ret.substring(0, ret.length()-2);
		return ret;
	}
	
	public boolean checkFlight(Flight_2017336_2017110 flight) {
		return this.myFlights.contains(flight);
	}

	public void removeFlight(Flight_2017336_2017110 flight) {
		this.myFlights.remove(flight);
	}
}
