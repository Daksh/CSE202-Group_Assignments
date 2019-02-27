import java.util.ArrayList;

public class Passenger_2017336_2017110 {
	int id;
	ArrayList<Integer> myFlights = new ArrayList<Integer>();
	
	public Passenger_2017336_2017110(int i) {
		this.id = i;
	}
	
	public void addFlight(Flight_2017336_2017110 f) {
		this.myFlights.add(f.id);
	}

	public String getAllFlights() {
		String ret = "";
		for(int i = 0; i<this.myFlights.size(); i++)
			ret+=this.myFlights.get(i)+", ";
		if(ret.length()>2)
			return ret.substring(0, ret.length()-2);
		return ret;
	}
	
	public boolean checkFlight(int flight_id) {
		return this.myFlights.contains(flight_id);
	}

	public void removeFlight(int flight_id) {
		this.myFlights.remove((Object)flight_id);
	}
}
