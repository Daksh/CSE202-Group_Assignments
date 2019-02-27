import java.util.ArrayList;
import java.util.Random;

public class Flight_2017336_2017110 {
	int id;
	int capacity;
	ArrayList<Integer> passengersList = new ArrayList<Integer>();
	
	Random rand = new Random();
	
	public Flight_2017336_2017110(int i) {
		this.id = i;
		this.capacity = 10+rand.nextInt(Main_2017336_2017110.NUM_PASSENGERS);
		if(Main_2017336_2017110.printDesc)
			System.out.println("Creating a flight: ID "+this.id+"\t capacity "+this.capacity);
	}
	
	public void addPassenger(int passenger_id) {
		this.passengersList.add(passenger_id);
	}
	
	public boolean possibleAddition() {
		return this.passengersList.size()<this.capacity;
	}

	public boolean has(int pid) {
		boolean exists = false;
		for(int i=0; i<this.passengersList.size(); i++) {
			if(this.passengersList.get(i)==pid)
				exists = true;
		}
		return exists;
	}
	
	public boolean checkPassenger(int passenger_id) {
		return this.passengersList.contains(passenger_id);
	}

	public void removePassenger(int passenger_id) {
		this.passengersList.remove((Object)passenger_id);
	}
	
}
