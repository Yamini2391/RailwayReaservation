package railwayReservation;
import java.util.*;
public class Admin {
public static LinkedList<Train> trainList = new LinkedList<>();
String securityKey;

	void displayAdminMenu() {
		int choice = 0;
		do {
			System.out.println("1. Add  trains ");
			System.out.println("2. Remove trains.");
			System.out.println("3. Display all trains.");
			System.out.println("4. Business analysis");//te functions nantar decide karu
			System.out.println("5. Exit");
			Scanner s = new Scanner(System.in);
			System.out.println("Enter your choice");
			choice = s.nextInt(); s.nextLine();
			switch(choice) {
			case 1: this.addTrains();
					break;
			case 2: this.removeTrains();
					break;
			case 3: this.displayTrains();
					break;
			case 4: System.out.println("What do you want to analyze?");
					break;
			case 5: System.out.println("Closing admin window....");
					break;
			default: System.out.println("Invalid choice!");
						break;
			}
		}while(choice!=5);
	}
	
	public void addTrains() {
		Scanner sc=new Scanner(System.in);
		char choice;
		do {
		System.out.println("Enter the train number");	
		int trainNo=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the name of the train");
		String trainName=sc.nextLine();
		System.out.println("Enter the source place of the train");
		String source=sc.nextLine();
		System.out.println("Enter the destination place of the train");
		String destination=sc.nextLine();
		System.out.println("Enter date of journey:");
		String date = sc.nextLine();
		System.out.println("Enter departure time:");
		String departure = sc.nextLine();
		System.out.println("Enter arrival time:");
		String arrival = sc.nextLine();
		System.out.println("Enter the maximum capacity of the train");
		int maxSeat=sc.nextInt();
		System.out.println("Enter the maximum waiting list capacity of the train");
		int maxWait=sc.nextInt(); sc.nextLine();
		System.out.println("Is the train sleepercoach?:y/n");
		String slp = sc.nextLine();
		boolean sl = false;
		if(slp.equals("y")||slp.equals("Y")) {
			sl = true;
		}
		System.out.println("Is the train A/C?:y/n");
		String ac = sc.nextLine();
		boolean a = false;
		if(ac.equals("y")||ac.equals("Y")) {
			a = true;
		}
		System.out.println("Enter price of ticket:");
		int price = sc.nextInt(); sc.nextLine();
		Train t=new Train(trainNo,trainName,a,sl,date,source,destination,maxSeat,maxWait, departure, arrival,price);
		trainList.add(t);
		System.out.println("Train "+trainNo+" "+trainName+" added successfully");
		
		System.out.println("Do you want to continue adding trains?y/n");
		choice=sc.next().charAt(0);
		//sc.close();
		
		}while(choice=='y'||choice=='Y');
	}
	
	public void removeTrains() {
		Scanner scan = new Scanner(System.in);
		int trainNo;
		int flag=0;
		String ch = "y";
		do {
			flag = 0;
			System.out.println("Enter the train number which you want to remove:");
			trainNo = scan.nextInt();
			for(Train t1: trainList) {
				if(t1.trainNo==trainNo) {
					trainList.remove(t1);
					System.out.println("Train no. "+trainNo+" successfully removed.");
					flag=1;
					break;
				}
			}
			if(flag!=1) {
				System.out.println("Train not found.");
			}
			System.out.println("Do you want to continue removing trains? y/n");
			ch = scan.next();
		}while(ch.equals("y")||ch.equals("Y"));
		System.out.println("Successfully removed.");
		//scan.close();
	}
	void displayTrains() {
		if(trainList.size()==0) {
			System.out.println("No trains are added yet. Please add the trains to check the availability.");
		}
		else {
			System.out.println(" ________________________________________________________________________________________________________________________");//85
			System.out.println("|no.|        name        |   date   |     source    |  destination  |Arrival|departure|price|  ac  |sleeper|seats|waiting|");
			System.out.println("|___|____________________|__________|_______________|_______________|_______|_________|_____|______|_______|_____|_______|");
			for(Train t : trainList) {
				System.out.format("|%3d|%20s|%10s|%15s|%15s|%7s|%9s|%5d|%6s|%7s|%5d|%7d|\n",t.trainNo,t.trainName,t.date,t.Source,t.Destination,t.arrivalTime,t.departureTime,t.price,t.ac,t.sleeperCoach,t.maxSeat,t.maxWait);
			}
			System.out.println("|___|____________________|__________|_______________|_______________|_______|_________|_____|______|_______|_____|_______|");
		}
	}
}

