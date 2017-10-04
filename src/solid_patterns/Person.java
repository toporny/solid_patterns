package solid_patterns;

public abstract class Person {
	  private String Firstname = "";
	  private String Lastname = "";
	  private String EmailAddress = "";
	  private String MobileNumber = "";
	  
	  public void setFirstname(String Firstname) {
	    this.Firstname = Firstname;
	  }
	  
	  public String getLastname() {
	    return this.Firstname;
	  }
	  
	  public void setLastname(String Lastname) {
	    this.Lastname = Lastname;
	  }
	  
	  public String getFirstname() {
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
	  
	  // System.out.println(getTyp()+" "+getName()+" mówi "+getVoice());
	}