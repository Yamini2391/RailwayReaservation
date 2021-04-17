package railwayReservation;
import java.util.*;
import java.time.*;
public class Train {
		int trainNo;
		String trainName;
		boolean ac;
		boolean sleeperCoach;
		String date;//validation
		String Source;
		String Destination;
		int maxSeat;
		int maxWait;
		String departureTime;
		String arrivalTime;
		int price;
		boolean[] seatList = new boolean[maxSeat]; // by default initialized to false, so if seatList[i]==false=>seat no. i+1 is vacant (not occupied).
		static int bookingCount=0;
		LinkedList<Seat> seatedPassengers = new LinkedList<>();
		Queue<Passenger> waiting = new LinkedList<>(); 
		//LocalDate d = LocalDate.parse(date);

		Train(int trainNo,String trainName,	boolean ac,	boolean sleeperCoach,String date,String Source,	String Destination,int maxSeat,int maxWait, String departure, String arrival, int price){
			this.trainNo=trainNo;
		    this.trainName=trainName;
		    this.ac=ac;
		    this.sleeperCoach=sleeperCoach;
		    this.date=date;
		    this.Source=Source;
		    this.Destination=Destination;
		    this.maxSeat=maxSeat;
		    this.maxWait=maxWait;
		    this.departureTime = departure;
		    this.arrivalTime = arrival;
		    this.price = price;
		    this.seatList = new boolean[maxSeat];
		}
		
		void bookTicket(String name, String gender, int age) {
			int currBookingID=0;
			if(seatedPassengers.size()==maxSeat) {
					/*if(waiting.size()==maxWait) {
						System.out.println("Reservation full. Waiting full.");
						}*/
					//else
						currBookingID = ++bookingCount;
						Passenger p1=new Passenger(name,age,gender,currBookingID);
						waiting.add(p1);
						System.out.println("You have been added to waiting list.");
						displayTicket(currBookingID);
					
				}
				else {
					currBookingID = ++bookingCount;
					int seatNo;
					int i=0;
					for(i=0;i<maxSeat;i++) {
						if(!seatList[i]) {
							break;
						}
					}
					seatNo = i+1;
					seatList[i] = true;
					Seat s1=new Seat(name, age, gender, currBookingID, seatNo, false);
					seatedPassengers.add(s1);
					System.out.println("Booking confirmed. Happy Journey!");
					displayTicket(currBookingID);
				}
		}
		
		void cancel(int bookingID) {
			int i;
			int flag = 0;
			Seat s;
			int seatNo;
			Passenger lucky;
			for(i=0;i<seatedPassengers.size();i++) {
				s = seatedPassengers.get(i);
				if(s.bookingID==bookingID) {
					seatNo = seatedPassengers.get(i).seatNo;
					seatedPassengers.remove(i);
					flag=1;
					System.out.printf("Your booking has been cancelled. You will receive a refund of Rs. %.2f\n",price*0.7);
					seatList[seatNo-1] = false;
					if(!waiting.isEmpty()) {
						lucky = waiting.poll();
						Seat newSeat = new Seat(lucky.passengerName, lucky.age, lucky.gender, lucky.bookingID, seatNo, true);
						seatedPassengers.add(newSeat);
						seatList[seatNo-1] = true;
					}
				}
			}		
			for(Passenger p : waiting) {
				if(p.bookingID==bookingID) {
					waiting.remove(p);
					flag=1;
					System.out.printf("Your booking has been cancelled. You will receive a refund of Rs. %.2f\n",price*0.7);
				}
			}
			if(flag==0) {
				System.out.println("No such booking found.");
			}
		}
		
		void displayStatus(int bookingID) {
			int i=0;
			int flag = 0;
			for(Passenger p : waiting) {
				i++;
				if(p.bookingID==bookingID) {
					System.out.println("Your position in waiting Queue is "+i);
					flag=1;
					break;
				}
			}
			if(flag==0) {
				System.out.println("Your booking is confirmed.");
			}
			displayTicket(bookingID);
		}
		
		void displayTicket(int bookingID) {
			//System.out.println("Your Ticket details are as follows:\n");
			System.out.println("Train No : "+trainNo);
			System.out.println("Train Name : "+trainName);
			for(Seat s : seatedPassengers) {
				if(s.bookingID==bookingID) {
					System.out.println("Booking ID :"+s.bookingID);
					System.out.println("Seat No. : "+s.seatNo);
					System.out.println("Passenger Name :"+s.passengerName);
					System.out.println("Age : "+s.age);
					System.out.println("Gender : " + s.gender);
				}
			}
			
			for(Passenger p : waiting) {
				if(p.bookingID==bookingID) {
					System.out.println("Booking ID :"+p.bookingID);
					System.out.println("Passenger Name :"+p.passengerName);
					System.out.println("Age : "+p.age);
					System.out.println("Gender : " + p.gender);
				}
			}
			System.out.println("Source : "+Source);
			System.out.println("Destination: "+Destination);
			System.out.println("Date of journey: "+date);
			System.out.println("Arrival Time : "+arrivalTime);
			System.out.println("Departure Time : "+departureTime);
			System.out.println("Ticket price: "+price);
		}
}
