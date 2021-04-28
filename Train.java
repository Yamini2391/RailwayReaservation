package railways;
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
					currBookingID = ++bookingCount;
					Passenger p1=new Passenger(name,age,gender,currBookingID);
					waiting.add(p1);
					System.out.println("\n\t\tYou have been added to waiting list.");
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
				System.out.println("\n\t\tBooking confirmed. Happy Journey!");
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
					System.out.printf("\n\t\tYour booking has been cancelled. You will receive a refund of Rs. %.2f\n",price*0.7);
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
					System.out.printf("\n\t\tYour booking has been cancelled. You will receive a refund of Rs. %.2f\n",price*0.7);
				}
			}
			if(flag==0) {
				System.out.println("\n\t\tNo such booking found.");
			}
		}
		
		void displayStatus(int bookingID) {
			int i=0;
			int flag = 0;
			/*if(seatedPassengers.size()==0) {
				System.out.println("Train cancelled");
				return;
			}*/
			for(Passenger p : waiting) {
				i++;
				if(p.bookingID==bookingID) {
					System.out.println("\n\t\tYour position in waiting Queue is "+i);
					flag=1;
					break;
				}
			}
			if(flag==0) {
				System.out.println("\n\t\tYour booking is confirmed.");
			}
			displayTicket(bookingID);
		}
		
		void displayTicket(int bookingID) {
			//System.out.println("Your Ticket details are as follows:\n");
			System.out.println("\n\t\tTrain No : "+trainNo);
			System.out.println("\t\tTrain Name : "+trainName);
			for(Seat s : seatedPassengers) {
				if(s.bookingID==bookingID) {
					System.out.println("\t\tBooking ID :"+s.bookingID);
					System.out.println("\t\tSeat No. : "+s.seatNo);
					System.out.println("\t\tPassenger Name :"+s.passengerName);
					System.out.println("\t\tAge : "+s.age);
					System.out.println("\t\tGender : " + s.gender);
				}
			}
			
			for(Passenger p : waiting) {
				if(p.bookingID==bookingID) {
					System.out.println("\t\tBooking ID :"+p.bookingID);
					System.out.println("\t\tPassenger Name :"+p.passengerName);
					System.out.println("\t\tAge : "+p.age);
					System.out.println("\t\tGender : " + p.gender);
				}
			}
			System.out.println("\t\tSource : "+Source);
			System.out.println("\t\tDestination: "+Destination);
			System.out.println("\t\tDate of journey: "+date);
			System.out.println("\t\tArrival Time : "+arrivalTime);
			System.out.println("\t\tDeparture Time : "+departureTime);
			System.out.println("\t\tTicket price: "+price);
		}
}
