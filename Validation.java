 package railwayReservation;
import java.util.*;
import java.util.regex.*;
public class Validation {
	public boolean dateValidation(String date) { 
	String regexDate="^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/(20[0-9][0-9])$";		
	Pattern pattern = Pattern.compile(regexDate);
	Matcher m=pattern.matcher(date);
	boolean isValid=m.matches();
	return isValid;
	
	
	//String validity;
	
	//validity=m.matches()?  "valid" : "not valid" ;
	//return validity;
	//System.out.println("The date is " + validity);
}
	public boolean timeValidation(String time) {
		String regexTime="^([0-1][0-9]|2[0-3]):([0-5][0-9])$";
		Pattern pattern = Pattern.compile(regexTime);
		Matcher m=pattern.matcher(time);
		boolean isValid=m.matches();
		return isValid;
	}
	public boolean placeValidation(String place) {
		String regexPlace="^[a-zA-Z]+$";
		Pattern pattern = Pattern.compile(regexPlace);
		Matcher m=pattern.matcher(place);
		boolean isValid=m.matches();
		return isValid;
	}
}
