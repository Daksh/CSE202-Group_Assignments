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
public class Transaction_Thread_2017336_2017110 {

}
