package d16127504;

import java.util.Map;

// absctract class (Person)
// one abstract method /Map/ must be inherited

public abstract class Person { 
	  private String Firstname = "";
	  private String Lastname = "";
	  private String EmailAddress = "";
	  private String MobileNumber = "";
	  
	  public Person (String Firstname, String Lastname, String EmailAddress, String MobileNumber) {
		  this.setFirstname(Firstname);
		  this.setLastname(Lastname);
		  this.setEmailAddress(EmailAddress);
		  this.setMobileNumber(MobileNumber);
	  }
	  
	  // overrided constructor. used for building object from scratch
	  public Person () {
	  }
	  	  
	  
	  public void setFirstname(String Firstname) {
	    this.Firstname = Firstname;
	  }

	  public String getFirstname() {
	    return this.Firstname;
	  }
	  

	  public void setLastname(String Lastname) {
	    this.Lastname = Lastname;
	  }
	  
	  public String getLastname() {
	    return this.Lastname;
	  }

	  
	  public void setEmailAddress(String EmailAddress) {
	    this.EmailAddress = EmailAddress;
	  }
	  
	  public String getEmailAddress() {
	    return this.EmailAddress;
	  }  
	  
	  public void setMobileNumber(String MobileNumber) {
	    this.MobileNumber = MobileNumber;
	  }
	  
	  public String getMobileNumber() {
	    return this.MobileNumber;
	  }

	  abstract public Map<String, String> getAllDetails();

	}