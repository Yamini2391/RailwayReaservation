package railwayReservation;

public class Seat extends Passenger {
	int seatNo;
	Boolean window;
	public Seat(String passengerName, int age, String gender, int bookingID,int seatNo,	Boolean window) {
		super(passengerName, age, gender, bookingID);
		this.seatNo=seatNo;
		this.window=window;
	}

}
