
package railwayReservation;
import java.util.*;

public class Accounts {
	LinkedList<User>userList=new LinkedList<>();
	Accounts(){
		
	}
	Scanner sc=new Scanner(System.in);
	
	void createAccount() {
		int flag=0;
		do{
		flag=0;
		System.out.println("Enter the username you want to use for your account");
		String userName = sc.nextLine();
		for(User u: userList) {
			if(u.userName.equals(userName)) {
				System.out.println("Already exists. Try a new username.");
				flag=1;
				break;
			}
		}
		if(flag==0){
		System.out.println("Enter the password to secure your account");
		String password = sc.nextLine();
		User u1 = new User(userName,password);
		
		userList.add(u1);
		u1.userMenu();
		}
		
		}while(flag==1);
		
	}

	public void login() {
		int flag=0, trying=0;
		do{
			System.out.println("Enter the userName");
			String userName=sc.nextLine();
			System.out.println("Enter the password");
			String password=sc.nextLine();
			User u1 = null;
			for(User u:userList) {
				if(u.userName.equals(userName) && u.password.equals(password) ){
					flag=1;
					u1=u;
					break;
				}
			}
			if(flag==1) {
				System.out.println("Login Successful");
				u1.userMenu();
			}
			else {
				System.out.println("Please enter proper credentials");
				System.out.printf("Do you want to keep trying?\n1.Yes\n2.Exit");
		    	trying = sc.nextInt();
		    	sc.nextLine();
			}
		}while(flag==0 && trying==1);	
	}
}
