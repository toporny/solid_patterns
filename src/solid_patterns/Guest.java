package solid_patterns;

import solid_patterns.Interfaces.IGuest;

class Guest extends Person implements IGuest{

	  private String Contact;
	  private String Company;
	  
	  
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

	}

	// https://www.youtube.com/watch?v=aTZ3-7myAJM  .super systax description


// General Employee           Contractor      Guest

// Firstname                  Firstname       Firstname
// Lastname                   Lastname        Lastname
// Email address              Email address   Email address
// Mobile Number              Mobile Number   Mobile Number


// Date of Birth              Date of Birth
// Job Title
// Salary
//	                          Contact         Contact
//	                          Company         Company

	//public interface Imessage {
	//  public string getwrwer();
	//}