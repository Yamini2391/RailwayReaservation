package railwayReservation;
import java.util.*;
public class User {
	
		int userID;
		String userName;
		String password;
		User(String userName,String password){
			this.userName=userName;
			this.password=password;
		}
		LinkedList<Booking> bookingList = new LinkedList<Booking>();
		Scanner sc = new Scanner(System.in);
		public void displayAvailableTrains(String source, String destination, String date) {
			int flag=0;
			for(Train t: Admin.trainList) {
				flag=0;
				if((t.Source).equals(source)&&(t.Destination).equals(destination)&&(t.date).equals(date)) {
					flag=1;
					System.out.println("Train no.: "+t.trainNo);
					System.out.println("Train Name"+t.trainName);
					System.out.println("From: "+t.Source);
					System.out.println("Departure time: "+t.departureTime);
					System.out.println("To: "+t.Destination);
					System.out.println("Arrival time: "+t.arrivalTime);
					System.out.println("Date of journey: "+t.date);
					System.out.println("Ticket price: Rs."+t.price);
					if(t.ac){
						System.out.println("A/C");
					}
					if(t.sleeperCoach) {
						System.out.println("Sleepercoach");
					}
					System.out.println("Number of available seats: "+(t.maxSeat-(t.seatedPassengers).size()));
					System.out.println("Waitings available: "+(t.maxWait-(t.waiting).size()));
				}
			}
			if(flag==1) {
				System.out.println("Enter train number which you want to book:");
				int n = sc.nextInt();sc.nextLine();
				int i=0;
				for(i=0;i<(Admin.trainList).size();i++) {
					if(Admin.trainList.get(i).trainNo==n) {
						break;
					}
				}
				System.out.println("Please enter following details:");
				System.out.println("Name of passenger:");
				String name = sc.nextLine();
				System.out.println("Gender:");
				String gender = sc.nextLine();
				System.out.println("Age:");
				int age = sc.nextInt();sc.nextLine();
				Admin.trainList.get(i).bookTicket(name, gender, age);
				int id = Train.bookingCount;
				Booking b = new Booking(n, id);
				bookingList.add(b);
			}
			else {
				System.out.println("No trains available. :(");
			}
		}
		public void userBook() {
			System.out.println("Enter source:");
			String source = sc.nextLine();
			System.out.println("Enter destination:");
			String destination = sc.nextLine();
			System.out.println("Enter date of journey:");
			String date = sc.nextLine();
			displayAvailableTrains(source, destination, date);
		}
		
		public void userStatus() {
			System.out.println("Enter your booking ID");
			int id = sc.nextInt();sc.nextLine();
			int trainNo = 0;
			for(Booking b : bookingList) {
				if(b.bookingID==id) {
					trainNo=b.trainNo;
					break;
				}
			}
			if(trainNo==0) {
				System.out.println("No such booking found");
			}
			for(Train t : Admin.trainList) {
				if(t.trainNo==trainNo) {
					t.displayStatus(id);
					break;
				}
			}
		}
		
		public void userCancel() {
			System.out.println("Enter the booking id to cancel the booking");
			int bookingID=sc.nextInt();sc.nextLine();
			int trainNo = 0;
			for(Booking b:bookingList) {
				if(b.bookingID==bookingID) {
					trainNo=b.trainNo;
					bookingList.remove(b);
					break;
				}
			}
			if(trainNo==0) {
				System.out.println("No such booking found");
			}
			else {
			for(Train t:Admin.trainList) {
				if(t.trainNo==trainNo) {
					t.cancel(bookingID);
				}
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
				choice=sc.nextInt();sc.nextLine();
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
