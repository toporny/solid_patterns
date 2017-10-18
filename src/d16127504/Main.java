package d16127504;
import java.util.ArrayList;
import java.util.logging.Logger;

import d16127504.EventServices.Helper;
import d16127504.EventServices.StorageCsvService;
import d16127504.EventServices.StorageDbService;
import d16127504.Interfaces.IStorageServices;
public class Main {

	
	
	public static void main(String[] args) {
		Logger LOGGER = Logger.getLogger("InfoLogging");
		LOGGER.info("Start of program....");		


		// "It is suggested that you use an ArrayList to store the event attenders."
		ArrayList<Person> people_list = new ArrayList<Person>();
		
		// Few example records. This is Hardcoded data. Each (object) has common methods (inherited from person abstract class)
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
				
		// save ONE record to storage
		csvServ.saveOneRecord(((Person) people_list.get(0)).getAllDetails()); 

		// let me add more records to the list...
		people_list.add(con1);
		people_list.add(gue1);
		people_list.add(gen2);
		people_list.add(con2);
		people_list.add(gue2);
		
		// save WHOLE list to the storage by one call
		csvServ.saveManyRecords(Helper.skipFirstOne(people_list)); // skipped because first row was added before. 
		
		
		// "At some point this will be changed upgraded to use a SQLite database."
		// Time to change "csvServ" to "dbServ" ...
		IStorageServices dbServ = new StorageDbService();
		dbServ.saveOneRecord(((Person) people_list.get(0)).getAllDetails()); // this is example how to save only ONE record to db
		dbServ.saveManyRecords(Helper.skipFirstOne(people_list)); // example how to save WHOLE list by one call

		
		// The application must be able to display a list of the people attending the event
		// by listing firstname, lastname and mobile number
		System.out.println("List of visitors: ");
		Helper.displayVisitors(people_list);

		
		/*
		 * THIS IS SECOND PART OF THIS PROJECT 
		 * "When the event is over the system should send each guest a text message and an email thanking them for"
		 * "attending the event  1.send thankfull SMS message, 2. send thankfull mail message"
		 */
		LOGGER.info("Second part of project, part A. Get data from DB and send notifications");		
		
		people_list.clear(); // clear all old data to be sure all new data comes from storage.
		people_list = dbServ.readAllData(); // read data from DB
		Helper.sendSmsToEverybody("+353 86 105 5566", people_list, "this is SMS to people from --DB--. Thank you for visiting!"); // +353 86 105 5566 = company phone 
		Helper.sendEmailToEverybody("event@company.com", people_list,  "this is EMAIL to people from --DB--. Thank you for visiting!"); // event@company.com = company email

		csvServ.close();
		dbServ.close();
		
		
		/*
		 * The same operation as above but this time take data from CSV file.
		 * Is not neccesairly in relase final project but I wanted to show how my CSV parser works.
		 * What this part of code does it parses CSV file and put data into (people_list) ArrayList 
		 * and send SMS and email after that.
		 */
		LOGGER.info("Second part of project, part B. Get data from CSV and send notifications");
		people_list.clear(); // clear all old data to be sure all new data comes from storage.
		IStorageServices csvTesing = new StorageCsvService(); // make another file handler
		people_list = csvTesing.readAllData(); // read data from CSV
		Helper.sendSmsToEverybody("+353 86 105 5566", people_list, "this is SMS to people from --CSV file--. Thank you for visiting!"); // +353 86 105 5566 = company phone 
		Helper.sendEmailToEverybody("event@company.com", people_list,  "this is EMAIL to people from --CSV-- file. Thank you for visiting!"); // event@company.com = company email
		csvTesing.close();		
		
		LOGGER.info("End of program. Be aware that next run appends data to storage (DB and CSV).\nFor testing purposes you may delete d16127504.sqlite and csv files");
	}

}

