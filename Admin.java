
package railwayReservation;
import java.util.*;
public class Admin {
public static LinkedList<Train> trainList = new LinkedList<>();
String securityKey="admin123";

	void displayAdminMenu() {
		int choice = 0;
		do {
			System.out.println("1. Add  trains ");
			System.out.println("2. Remove trains.");
			System.out.println("3. Display all trains.");
			System.out.println("4. Business analysis");//te functions nantar decide karu
			System.out.println("5. Exit");
			Scanner s = new Scanner(System.in);
			int status=0;
			do {
				status=0;
				try {
					System.out.println("Enter your choice");
					choice = s.nextInt(); //s.nextLine();
				}catch(InputMismatchException e) {
					System.out.println("Please enter correct choice. Your input does not match the choice.");
					status=1;
					s.nextLine();
				}
			}while(status==1);
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
		Validation v=new Validation();
		do {
		System.out.println("Enter the train number");	
		int trainNo=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter the name of the train");
		String trainName=sc.nextLine();
		
		boolean isSrc=false; boolean isDest=false;
		String source,destination;
		do {
		do {
		System.out.println("Enter the source place of the train");
		 source=sc.nextLine();
		isSrc=v.placeValidation(source);
		if(!isSrc) {
			System.out.println("Please enter the correct source");
		}
		}while(!isSrc);
		
		//boolean isDest=false;
		do {
		System.out.println("Enter the destination place of the train");
		 destination=sc.nextLine();
		isDest=v.placeValidation(destination);
		if(!isDest) {
			System.out.println("Please enter the correct destination");
		}
		}while(!isDest);
		if(source.equalsIgnoreCase(destination)) {
			System.out.println("Please enter correct source and destination");
		}
		}while(source.equalsIgnoreCase(destination));
		
		//Validation v=new Validation();
		boolean isValid=false; String date;//int status=0;
		do {
		System.out.println("Enter date of journey in dd/mm/yyyy format only:");
		 date = sc.nextLine();
		isValid=v.dateValidation(date);
		if(!isValid) {
			System.out.println("The date format is invalid. Please enter the date in dd/mm/yyyy format only");
		}
		
		}while(!isValid);
		
		/*System.out.println("Enter date of journey:");
		String date = sc.nextLine();*/
		
		boolean deptTime=false; String departure;
		do {
		System.out.println("Enter departure time in 24 hour format only hh:mm ");
		 departure = sc.nextLine();
		deptTime=v.timeValidation(departure);
		if(!deptTime) {
			System.out.println("The time format is invalid. Enter it in correct format");
		}
		//sc.nextLine();
		}while(!deptTime);
		
		
		/*System.out.println("Enter arrival time:");
		String arrival = sc.nextLine();*/
		boolean arrTime=false; String arrival;
		do {
			System.out.println("Enter arrival time in 24 hour format only hh:mm ");
			 arrival = sc.nextLine();
			arrTime=v.timeValidation(arrival);
			if(!arrTime) {
				System.out.println("The time format is invalid. Enter it in correct format");
			}
			//sc.nextLine();
			}while(!arrTime);
		
		
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

