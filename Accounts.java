package railwayReservation;
import java.util.*;

public class Accounts {
	LinkedList<User>userList=new LinkedList<>();
	Accounts(){
		
	}
	Scanner sc=new Scanner(System.in);
	
	void createAccount() {
		System.out.println("Enter the username you want to use for your account");
		String userName = sc.nextLine();
		System.out.println("Enter the password to secure your account");
		String password = sc.nextLine();
		User u = new User(userName,password);
		userList.add(u);
		u.userMenu();
	}

	public void login() {
		int flag=0;
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
			}
		}while(flag==0);	
	}
}
