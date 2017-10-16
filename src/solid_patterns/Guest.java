package solid_patterns;


import java.util.HashMap;
import java.util.Map;

import solid_patterns.Interfaces.IGuest;
import solid_patterns.Interfaces.StorageDetails;

class Guest extends Person implements IGuest, StorageDetails {

	private String Contact;
	private String Company;

	private final String role = "Guest";
	  
	public Guest(String Firstname, String Lastname, String EmailAddress, String MobileNumber, String Contact, String Company) {
		super(Firstname, Lastname, EmailAddress, MobileNumber);
		this.setContact(Contact);
		this.setCompany(Company);
	}

	public void setContact(String Contact) {
		this.Contact = Contact;
	}

	public String getContact() {
		return this.Contact;
	}


	public void setCompany(String Company) {
		this.Company = Company;
	}

	public String getCompany() {
		return this.Company;
	}

 
	public String getRole() {
		return this.role;
	}

	
	final public Map<String, String> getAllDetails(){
		Map<String, String> m = new HashMap<String, String>();
		m.put("firstname",      this.getFirstname());
		m.put("lastname",       this.getLastname());
		m.put("email_address",  this.getEmailAddress());
		m.put("mobile_number",  this.getMobileNumber());		
		m.put("contact",        this.getContact());
		m.put("company",        this.getCompany());
		return m;
	}    

	

}

// https://www.youtube.com/watch?v=atz3-7myajm  .super.


// general employee           contractor      guest

// firstname                  firstname       firstname
// lastname                   lastname        lastname
// email_address              email_address   email_address
// mobile_number              mobile_number   mobile_number


// date_of_birth              date_of_birth
// job_title
// salary
//	                          contact         contact
//	                          company         company


	//public interface Imessage {
	//  public string getwrwer();
	//}