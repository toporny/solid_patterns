package solid_patterns;

import solid_patterns.Interfaces.IContractor;

class Contractor extends Person implements IContractor{
	  private String DateOfBirth;
	  private String Contact;
	  private String Company;
	  
	  
	  // zdublowane z GeneralEmployee.java  !!!
	  public void setDateOfBirth(String DateOfBirth) {
	    this.DateOfBirth = DateOfBirth;
	  }
	  
	  public String getDateOfBirth() {
	    return this.DateOfBirth;
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
	  
	  
	    
	}
