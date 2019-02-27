import java.util.ArrayList;
import java.util.Random;

public class Flight_2017336_2017110 {
	int id;
	int capacity;
	ArrayList<Passenger_2017336_2017110> passengersList = new ArrayList<Passenger_2017336_2017110>();
	
	Random rand = new Random();
	
	public Flight_2017336_2017110(int i) {
		this.id = i;
		this.capacity = 10+rand.nextInt(Main_2017336_2017110.NUM_PASSENGERS);
		System.out.println("Creating a flight: ID "+this.id+"\t capacity "+this.capacity);
	}
	
	public void addPassenger(Passenger_2017336_2017110 p) {
		assert p!=null;
		this.passengersList.add(p);
	}
	
	public boolean possibleAddition() {
		return this.passengersList.size()<this.capacity;
	}

	public boolean has(int pid) {
		boolean exists = false;
		for(int i=0; i<this.passengersList.size(); i++) {
			if(this.passengersList.get(i).id==pid)
				exists = true;
		}
		return exists;
	}
	
	public boolean checkPassenger(Passenger_2017336_2017110 passenger) {
		return this.passengersList.contains(passenger);
	}

	public void removePassenger(Passenger_2017336_2017110 passenger) {
		this.passengersList.remove(passenger);
	}
	
}
