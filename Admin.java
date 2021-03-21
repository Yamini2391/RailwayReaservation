package railwayReservation;
import java.util.*;
public class Admin {
	static LinkedList<Train> trainList=new LinkedList<>();
	String key = "admin123";
	public void displayTrains() {
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
	
	public void addTrains() {
		Scanner sc=new Scanner(System.in);
		char choice;
		do {
			System.out.println("Enter the train number");	
			int trainNo=sc.nextInt();
			sc.nextLine();
			System.out.println("Enter the name of the train");
			String trainName=sc.nextLine();
			System.out.println("Enter date of train");
			String date = sc.nextLine();
			System.out.println("Enter the source place of the train");
			String source=sc.nextLine();
			System.out.println("Enter the destination place of the train");
			String destination=sc.nextLine();
			System.out.println("Enter the maximum capacity of the train");
			int maxSeat=sc.nextInt();sc.nextLine();
			System.out.println("Enter the maximum waiting list capacity of the train");
			int maxWait=sc.nextInt();sc.nextLine();
			System.out.println("Enter departure time of train");
			String departureTime=sc.nextLine();
			System.out.println("Enter arrival time of train");
			String arrivalTime=sc.nextLine();
			System.out.println("Enter price of ticket");
			int price = sc.nextInt();sc.nextLine();
			System.out.println("Is the train AC? (Y/N)");
			String ac = sc.nextLine();
			boolean AC = (ac.equals("Y")||ac.equals("y")) ? true:false;
			System.out.println("Is the train Sleepercoach? (Y/N)");
			String s = sc.nextLine();
			boolean Sleeper = (s.equals("Y")||s.equals("y")) ? true:false;
			Train t=new Train(trainNo,trainName,AC,Sleeper,date,source,destination,maxSeat,maxWait,arrivalTime,departureTime,price);
			trainList.add(t);
			System.out.println("Do you want to keep adding Trains? (Y/N)");
			choice=sc.next().charAt(0);
			//sc.close();
		
		}while(choice=='y'||choice=='Y');
	}
	
	
	public void removeTrains(){
			Scanner scan = new Scanner(System.in);
			int trainNo;
			int flag=0;
			String ch = "y";
			do {
				flag=0;
				System.out.println("Enter the train number which you want to remove:");
				trainNo = scan.nextInt();scan.nextLine();
				for(Train t1: trainList) {
					if(t1.trainNo==trainNo) {
						trainList.remove(t1);
						flag=1;
						System.out.println("Successfully removed.");
						break;
					}
				}
				if(flag==0) {
					System.out.println("Train not found.");
				}
				System.out.println("Do you want to continue removing trains? y/n");
				ch = scan.nextLine();
			}while(ch.equals("y")||ch.equals("Y"));
			//scan.close();
		}
	
	void displayMenu() {
		int choice;
		Scanner s = new Scanner(System.in);
		do {
			System.out.println("1. Add  trains ");
			System.out.println("2. Remove trains.");
			System.out.println("3. Display all trains");
			System.out.println("4. Exit");
			System.out.println("Enter your choice");
			choice = s.nextInt();
			switch(choice) {
			case 1: addTrains();
					break;
			case 2: removeTrains();
					break;
			case 3: displayTrains();
					break;
			case 4: System.out.println("Closing Admin menu. Thanks.");
					break;
			default: System.out.println("Invalid choice!");
					break;
			}
		}while(choice!=4);
	}

}
