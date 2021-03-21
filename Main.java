package railwayReservation;
import java.util.*;

public class Main {
	public static void main(String args[]) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome to Indian Railways");
		Admin a=new Admin();
		Accounts acc=new Accounts();
		String securityKey;
		
		int choice;
		int ch;
		
		do {
			System.out.println("Select your role");
			System.out.println("1.Admin");
			System.out.println("2.User");
			System.out.println("3.Exit");
			choice=sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1: do{
				System.out.println("Enter the security key");
			
					
				    securityKey=sc.nextLine();
				    if(securityKey.equals("admin123")) {
				    System.out.println("Welcome, Admin!");
					a.displayAdminMenu();
				    }else {
				    	System.out.println("Enter correct security key to access admin");
				    }
			}while(!securityKey.equals("ADMIN123"));
					break;
			case 2: 
					System.out.println("Do you want to create new account or login into your existing account");
					System.out.println("1.Create Account");
					System.out.println("2.Login");
					System.out.println("3.Exit");
					ch=sc.nextInt();
					switch(ch) {
					case 1: acc.createAccount();
							break;
					case 2: acc.login();
							break;
					case 3: System.out.println("Thank you.Visit again");
							break;
					default: System.out.println("Invalid choice");		
					}
				   
				   break;
			case 3: System.out.println("Thank you ");
					break;
			default: System.out.println("Invalid choice");
			}
			
		}while(choice!=4);
	}



	
}

