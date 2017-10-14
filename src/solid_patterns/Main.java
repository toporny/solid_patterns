package solid_patterns;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    //GeneralEmployee g = new GeneralEmployee();
	    //System.out.println("hello world");

		ArrayList<Person> people_list = new ArrayList<Person>();
		//GeneralEmployee ge = new GeneralEmployee ("George", "Bush", "george_bush@usa.gov", "+123456789", "July 6, 1946", "president", 2300.12);
		   
		
		System.out.println("end of program");
	}

}


//System Requirements:
//- When a person is added to the system their details will be added/appended to a text file (csv).
//At some point this will be changed upgraded to use a SQLite database.
//Both the file functionality and the database functionality must be implemented in the system.
//- It is suggested that you use an ArrayList to store the event attenders.
//- The application must be able to display a list of the people attending the event by listing firstname, lastname and mobile number.
//- The application needs to be developed to offer maintainability and extendibility.
//- When the event is over the system should send each guest a text message and an email thanking them for
//attending the event (note: there is no need for the SMS and Email to be operational, a message of screen will suffice)
//- The application must be created using Eclipse or Netbeans and use a SQLite database..