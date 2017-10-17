package solid_patterns;
import java.util.ArrayList;
import solid_patterns.Helpers.StorageDbService;
import solid_patterns.Helpers.Helper;
import solid_patterns.Helpers.StorageCsvService;
import solid_patterns.Interfaces.IStorageServices;
import solid_patterns.Interfaces.StorageDetails;

public class Main {

	
	
	public static void main(String[] args) {
		System.out.println("===== Start of program =====");

		// "It is suggested that you use an ArrayList to store the event attenders."
		ArrayList<Person> people_list = new ArrayList<Person>();
		
		// Few example records. This is Hardcoded data.
		// Each (object) has common methods (inherited from person abstract ckass)
		// and different specific methods only for specific role"
		
		GeneralEmployee gen1 = new GeneralEmployee ("George", "Bush", "george_bush@usa.gov", "+123456789", "1946-07-04", "president", "2300.12");
		Contractor      con1 = new Contractor      ("Urlich", "von Braun", "urlich_von_braun@v8.de", "+34235433", "1911-03-21", "Beverly Hils 90210",  "NASA");
		Guest           gue1 = new Guest           ("Mieszko", "Pierwszy", "mieszko1@gov.pl", "+48 505 323 111", "ul. Polan 1",  "Sclavinia");
		GeneralEmployee gen2 = new GeneralEmployee ("Barbara", "Bush", "barbara_bush@usa.gov", "+234567890", "1948-07-04", "first lady", "1300.00");
		Contractor      con2 = new Contractor      ("Paul Joseph", "Goebbels", "joseph_goebbels@germany.gov", "+34 111 234", "1897-10-29", " Reich 1",  "Reichstag");
		Guest           gue2 = new Guest           ("Jan", "Kazimierz", "jan_kazimierz@gov.pl", "+342 35 433 11", "Kazimierz 1",  "Slavic inc.");

		
		// When a person is added to the system then details will be added/appended to a text file (csv).
		IStorageServices csvServ = new StorageCsvService();
		people_list.add(gen1);
				
		// example how to save only ONE record to storage
		csvServ.saveOneRecord(((Person) people_list.get(0)).getAllDetails()); 

		// let me add more records to the list...
		people_list.add(con1);
		people_list.add(gue1);
		people_list.add(gen2);
		people_list.add(con2);
		people_list.add(gue2);

		// example how to save save WHOLE list to the storage by one call
		csvServ.saveManyRecords(Helper.skipFirstOne(people_list)); // skipped because first row was added before. 
		csvServ.close();

		
		// "At some point this will be changed upgraded to use a SQLite database."
		IStorageServices dbServ = new StorageDbService();
		dbServ.saveOneRecord(((Person) people_list.get(0)).getAllDetails()); // this is example how to save only ONE record to db
		dbServ.saveManyRecords(Helper.skipFirstOne(people_list)); // example how to save WHOLE list by one call
		dbServ.close();
		// What I did above I changed "csvServ" to "dbServ"
		// and everything else is the same like with CSV was (D.I.P. Dependency Inversion Principle)

		System.exit(0);		

		
		
//		for (Object val : people_list) {
//			System.out.println(((StorageDetails) val).getRole());
//			System.out.println(((Person) val).getFirstname());
//			System.out.println(((Person) val).getLastname());
//			System.out.println(((Person) val).getEmailAddress());
//			System.out.println(((Person) val).getMobileNumber());
//		}
//		
		
		
		
		
		// "The application must be able to display a list of the people
		// attending the event by listing firstname, lastname and mobile number."
		// new helper.showEverybodylist(people_list);
		
		
		// When the event is over the system should send each guest a text message and an email thanking them for
		// attending the event (note: there is no need for the SMS and Email to be operational,
		// a message of screen will suffice)
		// 1. send thankfull SMS message
		// 2. send thankfull mail message
		
		
		
		// - The application must be created using Eclipse or Netbeans and use a SQLite database..
		
		
		
		
		
		
//		for (Object val : people_list) { // https://stackoverflow.com/questions/6386549/casting-foreach-java
//			StorageDetails mi = (StorageDetails) val;
//			
//			System.out.println(((StorageDetails) val).getRole());
//			//val.getFirstname();
//			//mi.doSomething();
//			mi.getRole();
//		}
		

		// open CSV file
		// to do...
		
		// appending records to CSV file
		
		// new helper.SaveData(people_list);
		
		// new helper.SaveToDB(people_list);		
		
//	    Connection c = null;
//	    Statement stmt = null;
//		try {
//
//	    	stmt = c.createStatement();
//	    	String createTable = "CREATE TABLE Students4 "+
//	    			" (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
//	    			" firstname CHAR(80) NOT NULL, "+
//	    			" lastname CHAR(80) NOT NULL, "+
//	    			" email_address CHAR(80) NOT NULL, "+
//	    			" mobile_number CHAR(80) NOT NULL, "+  // let set STRING because 'plus' on the beginning: "+353 861 055 543" 
//	    			" date_of_birth DATE, "+ 
//	    			" job_title CHAR(50), "+ 
//	    			" Salary REAL, "+
//	    			" contact CHAR(50) "+
//	    			" company CHAR(50) "+
//	    		")";
//	    	stmt.executeUpdate(createTable);
//	    	c.close();
//	    	
//	    } catch (Exception e) {
//	    	System.err.println(e.getClass().getName() + ":"+e.getMessage());
//	    	System.exit(0);
//	    }
		
		
		for (Object val : people_list) {
			System.out.println(((StorageDetails) val).getRole());
			System.out.println(((Person) val).getFirstname());
		}
		
		// close CSV file
		// to do...
		
		

		
	
		
		System.out.println("===== End of program =====");
	}

}

/*

General Employee

First name
Last name
Email address
Mobile Number
Date of Birth
Job Title
Salary
Contact
Company


  	// General Employee           Contractor      Guest

	// First name                 First name      First name
	// Last name                  Last name       Last name
	// Email address              Email address   Email address
	// Mobile Number              Mobile Number   Mobile Number


	// Date of Birth              Date of Birth
	// Job Title
	// Salary
	//	                          Contact         Contact
	//	                          Company         Company
 */
//  System Requirements:
//  - When a person is added to the system their details will be added/appended to a text file (csv).
//  At some point this will be changed upgraded to use a SQLite database.
//  Both the file functionality and the database functionality must be implemented in the system.
//# - It is suggested that you use an ArrayList to store the event attenders.
//  - The application must be able to display a list of the people attending the event by listing firstname, lastname and mobile number.
//  - The application needs to be developed to offer maintainability and extendibility.
//  - When the event is over the system should send each guest a text message and an email thanking them for
//  attending the event (note: there is no need for the SMS and Email to be operational,
//  a message of screen will suffice)
//  - The application must be created using Eclipse or Netbeans and use a SQLite database..






//ROLES AND PROPERITIES
//-----------------------------------------------------------

//general employee           contractor      guest
//================           ==========      ======
//firstname                  firstname       firstname
//lastname                   lastname        lastname
//email_address              email_address   email_address
//mobile_number              mobile_number   mobile_number
//date_of_birth              date_of_birth
//job_title
//salary
//                          contact         contact
//                          company         company




//while (rs.next()) {
//	int id = rs.getInt("id");
//	String firstname = rs.getString("firstname");
//	String lastname = rs.getString("lastname");
//	String coursename = rs.getString("coursename");
//	int age = rs.getInt("age");
//	String address = rs.getString("address");
//	
//	System.out.println("rwer");
//	System.out.println("id: " + id);
//	System.out.println("firstname: " + firstname);
//	System.out.println("lastname: " + lastname);
//	System.out.println("coursename: " + coursename);
//	System.out.println("age: " + age);
//	System.out.println("address: " + address);
//	System.out.println();
//	
//	
//}
