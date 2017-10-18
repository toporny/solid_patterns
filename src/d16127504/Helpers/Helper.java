package d16127504.Helpers;

import java.util.ArrayList;

import d16127504.Person;
import d16127504.Interfaces.IMsgProvider;

public class Helper {
	

	// what id does it returns people_list without first element.
	// I had to make copy of array list because ".remove(0)" deals on reference
	// Anyway.. this helper.skipFirstOne is only temporary function to show my saveOneRecord() and saveManyRecord()  
	public static ArrayList<Person> skipFirstOne(ArrayList<Person> people_list) {
		ArrayList<Person> copy = new ArrayList<Person>(people_list); 
		copy.remove(0);
		return copy;
	}

	
	public static void sendSmsToEverybody(String fromDetails, ArrayList<Person> people_list, String msgContent) {
		IMsgProvider senderService = new SmsService();
		for (Object val : people_list) {
			senderService.SendMsg(fromDetails, ((Person) val).getMobileNumber(), msgContent);
		}
	}

	public static void sendEmailToEverybody(String fromDetails, ArrayList<Person> people_list, String msgContent) {
		IMsgProvider senderService = new MailService();
		for (Object val : people_list) {
			senderService.SendMsg(fromDetails, ((Person) val).getEmailAddress(), msgContent);
		}
	}


	// The application must be able to display a list of the people attending the event
	// by listing firstname, lastname and mobile number
	public static void displayVisitors(ArrayList<Person> people_list) {
		for (Object val : people_list) {
			System.out.println(((Person) val).getFirstname()+' '+((Person) val).getLastname() + ", phone: "+((Person) val).getMobileNumber());
		}		
	}

		
	
}
