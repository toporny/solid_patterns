package d16127504;

import java.util.HashMap;
import java.util.Map;

import d16127504.Interfaces.IGeneralEmployee;
import d16127504.Interfaces.StorageDetails;


public class GeneralEmployee extends Person implements IGeneralEmployee, StorageDetails {
  private String DateOfBirth;
  private String JobTitle;
  private String Salary; // I made String instead double here to keep also currency $, €, pounds etc... 120000€
  
  private final String role = "GeneralEmployee";
  
  public GeneralEmployee(String Firstname, String Lastname, String EmailAddress, String MobileNumber, String DateOfBirth, String JobTitle, String Salary) {
    super(Firstname, Lastname, EmailAddress, MobileNumber);
    this.setDateOfBirth(DateOfBirth);
    this.setJobTitle(JobTitle);
    this.setSalary(Salary);
  }

  // used for building object from scratch. (during getting data from storage)
  public GeneralEmployee() {
  }
  
  
  // used for building object from scratch. (during getting data from storage)
  public GeneralEmployee(String Firstname, String Lastname, String EmailAddress, String MobileNumber) {
	  super(Firstname, Lastname, EmailAddress, MobileNumber);
  }



  public void setDateOfBirth(String DateOfBirth) {
    this.DateOfBirth = DateOfBirth;
  }
  
  public String getDateOfBirth() {
    return this.DateOfBirth;
  }
  

  public void setJobTitle(String JobTitle) {
    this.JobTitle = JobTitle;
  }
  
  public String getJobTitle() {
    return this.JobTitle;
  }

  
  public void setSalary(String Salary) {
    this.Salary = Salary;
  }
  
  public String getSalary() { // I made string here to keep also currency $, €, pounds etc...
    return this.Salary;
  }
  
  public String getRole() {
	return this.role; // it is equal to: return this.getClass().getSimpleName();
	// and it could be even move to Person /parent class/
	// I have decided to made additional variable (private final String role = "GeneralEmployee";) in this class/case.
	// to make Software localization possible in future
  }
  

  // this function gives every detail of person in hashmap
  // must be inherited from Parent Class /Person/  
  final public Map<String, String> getAllDetails() {
	Map<String, String> m = new HashMap<String, String>();
	m.put("role",      		this.getRole());
	m.put("firstname",      this.getFirstname());
	m.put("lastname",       this.getLastname());
	m.put("email_address",  this.getEmailAddress());
	m.put("mobile_number",  this.getMobileNumber());
	m.put("date_of_birth",  this.getDateOfBirth());
	m.put("job_title",      this.getJobTitle());
	m.put("salary",         this.getSalary());
	return m;
  }
}
