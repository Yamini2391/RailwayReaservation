package railwayReservation;
import java.util.*;
public class User {
	double userID;
	String userName;
	String password;
	LinkedList<Booking> bookingList = new LinkedList<Booking>();
	User(String userName, String password){
		this.userName = userName;
		this.password = password;
	}
	Scanner sc = new Scanner(System.in);
	public void displayAvailableTrains(String source, String destination, String date) {
		int flag=0;
		Train t1 = null;
		for(Train t: Admin.trainList) {
			if((t.Source).equals(source)&&(t.Destination).equals(destination)&&(t.date).equals(date)) {
				flag=1;
				System.out.println("\nTrain no.: "+t.trainNo);
				System.out.println("Train name: "+t.trainName);
				System.out.print("From: "+t.Source);
				System.out.println("    Departure time: "+t.departureTime);
				System.out.print("To: "+t.Destination);
				System.out.println("    Arrival time: "+t.arrivalTime);
				System.out.println("Date of journey: "+t.date);
				System.out.println("Ticket price: "+t.price);
				if(t.ac){
					System.out.println("A/C");
				}
				if(t.sleeperCoach) {
					System.out.println("Sleepercoach");
				}
				System.out.print("Number of available seats: ");
				System.out.println(t.maxSeat-(t.seatedPassengers).size());
				System.out.print("Waitings available: ");
				System.out.println(t.maxWait-(t.waiting).size());
				System.out.println();
				t1 = t;
				//break;
				/*if(t.maxWait==(t.waiting).size()) {
					flag=2;
					System.out.println("Reservation full. Waiting full.");
				}*/
			}
		}
		if(flag==1) {
			System.out.println("\nEnter train number which you want to book:");
			int n = sc.nextInt(); sc.nextLine();
			int i=0;
			for(i=0;i<(Admin.trainList).size();i++) {
				if(Admin.trainList.get(i).trainNo==n) {
					break;
				}
			}
			if(i==(Admin.trainList).size()) {
				System.out.println("Invalid train number. Try again.");
				return;
			}
			if(Admin.trainList.get(i).maxSeat-(Admin.trainList.get(i).seatedPassengers).size()==0 && Admin.trainList.get(i).maxWait-(Admin.trainList.get(i).waiting).size()==0) {
				System.out.println("\nThis train does not have any seats available.");
				System.out.println("Sorry for the incovenience.");
				return;
			}
			System.out.println("\nEnter Number of tickets you want to book: ");
			int tickets = sc.nextInt();sc.nextLine();
			boolean toBook = false;
			int seats = Admin.trainList.get(i).maxSeat-(Admin.trainList.get(i).seatedPassengers).size();
			int wait = Admin.trainList.get(i).maxWait-(Admin.trainList.get(i).waiting).size();
			String choice =null;
			if(tickets<=seats) {
				System.out.println("Tickets for "+tickets+" passengers are available.");
				toBook=true;
			}
			else if(tickets<=wait+seats) {
				if(seats==0) {
					System.out.println("No confirm tickets in this train are available.All your "+tickets+" tickets will be booked in waiting.\nDo you want to continue? (Y/n)");
					choice=sc.nextLine();
				}
				else {
					System.out.println("You will get "+seats+" tickets as confirmed and remaining "+(tickets-seats)+" tickets will be added to waiting.\nDo you want to continue? (Y/n)");
					choice=sc.nextLine();
				}
				toBook = choice.equals("Y")||choice.equals("y") ? true:false;
			}
			else { //tickets>wait+seats;
				System.out.println("This train does not have "+tickets+" tickets available.");
				if(seats==0) {
					System.out.println("You will get only "+wait+" tickets in waiting.\nDo you want to continue? (Y/n)");
					choice = sc.nextLine();
				}
				else {
					System.out.println("You will get "+seats+" tickets as confirmed and "+wait+" tickets in waiting.\nDo you want to continue? (Y/n)");
					choice = sc.nextLine();
				}
				toBook = choice.equals("Y")||choice.equals("y") ? true:false;
				tickets=wait+seats;
			}
			if(toBook) {
				List<Passenger> yourList = new LinkedList<>();
				System.out.println("\nPlease enter passenger details respectively:");
				for(int t=0;t<tickets;t++) {
					System.out.println("\nPassenger #"+(t+1));
					System.out.println("Name of passenger:");
					String name = sc.nextLine();
					System.out.println("Gender:");
					String gender = sc.nextLine();
					System.out.println("Age:");
					int age = sc.nextInt(); sc.nextLine();
					yourList.add(new Passenger(name,age,gender,0));
				}
				System.out.println("\nYour ticket details are as follows:");
				int t=1;
				for(Passenger p : yourList) {
					System.out.println("\nPassenger #"+t);
					Admin.trainList.get(i).bookTicket(p.passengerName, p.gender, p.age);
					int id = Train.bookingCount;
					Booking b = new Booking(n, id);
					bookingList.add(b);
					t++;
				}
				
			}
			else {
				System.out.println("\nSorry for the incovenience.");
			}
		}
		else if(flag==0) {
			System.out.println("\nNo trains available. :(");
		}
	}
	public void userBook() {
		System.out.println("\nEnter source:");
		String source = sc.nextLine();
		System.out.println("Enter destination:");
		String destination = sc.nextLine();
		System.out.println("Enter date of journey:");
		String date = sc.nextLine();
		displayAvailableTrains(source, destination, date);
	}
	public void userCancel() {
		System.out.println("\nEnter your booking ID");
		int id = sc.nextInt();sc.nextLine();
		int trainNo = 0;
		for(Booking b : bookingList) {
			if(b.bookingID==id) {
				trainNo=b.trainNo;
				bookingList.remove(b);
				break;
			}
		}
		if(trainNo==0) {
			System.out.println("\nNo such booking found");
		}
		for(Train t : Admin.trainList) {
			if(t.trainNo==trainNo) {
				t.cancel(id);
				break;
			}
		}
	}
	
	public void userStatus() {
		System.out.println("\nEnter your booking ID");
		int id = sc.nextInt();sc.nextLine();
		int trainNo = 0;
		for(Booking b : bookingList) {
			if(b.bookingID==id) {
				trainNo=b.trainNo;
				break;
			}
		}
		if(trainNo==0) {
			System.out.println("\nNo such booking found. Please enter a valid booking ID.");
		}
		for(Train t : Admin.trainList) {
			if(t.trainNo==trainNo) {
				t.displayStatus(id);
				break;
			}
		}
	}
	
	public void userMenu() {
		int choice;
		do {
			System.out.println("\nWelcome to your account");
			System.out.println("****MENU****");
			System.out.println("1.Book train ticket");
			System.out.println("2.Cancel train ticket");
			System.out.println("3.Display status of ticket");
			System.out.println("4.Exit");
			System.out.println("Enter your choice");
			choice=sc.nextInt(); sc.nextLine();
			switch(choice) {
			case 1:
				userBook();
				break;
			case 2:
				userCancel();
				break;
			case 3:
				userStatus();
				break;
			case 4:
				System.out.println("Thank you for using our services!!");
				break;
			default:
				System.out.println("Invalid Choice. Please try again");
			}
		}while(choice!=4);
	}

}
