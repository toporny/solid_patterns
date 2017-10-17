package solid_d16127504;
import java.util.HashMap;
import java.util.Map;
import solid_patterns.Interfaces.IGuest;
import solid_patterns.Interfaces.StorageDetails;

public class Guest extends Person implements IGuest, StorageDetails {

	private String Contact;
	private String Company;

	private final String role = "Guest";

	public Guest(String Firstname, String Lastname, String EmailAddress, String MobileNumber, String Contact, String Company) {
		super(Firstname, Lastname, EmailAddress, MobileNumber);
		this.setContact(Contact);
		this.setCompany(Company);
	}
	
	// used for building object from scratch. (during getting data from storage)
	public Guest() {
	}
	
	// used for building object from scratch. (during getting data from storage)
	public Guest(String Firstname, String Lastname, String EmailAddress, String MobileNumber) {
	  super(Firstname, Lastname, EmailAddress, MobileNumber);
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
		return this.role; // it is equal to: return this.getClass().getSimpleName();
		
		//  and it could be even move to Person /parent class/!
		// I have decided to made additional variable (private final String role = "GeneralEmployee";) in this class/case.
		// to make Software localization possible in future
	}  	

    // this function gives every detail of person in hashmap
	// must be inherited from Parent Class /Person/	
	final public Map<String, String> getAllDetails(){
		Map<String, String> m = new HashMap<String, String>();
		m.put("role",           this.getRole());
		m.put("firstname",      this.getFirstname());
		m.put("lastname",       this.getLastname());
		m.put("email_address",  this.getEmailAddress());
		m.put("mobile_number",  this.getMobileNumber());		
		m.put("contact",        this.getContact());
		m.put("company",        this.getCompany());
		return m;
	}    

}
