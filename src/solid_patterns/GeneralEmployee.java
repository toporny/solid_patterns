package solid_patterns;

import solid_patterns.Interfaces.IGeneralEmployee;


class GeneralEmployee extends Person implements IGeneralEmployee {
  private String DateOfBirth;
  private String JobTitle;
  private double Salary;
  
  
  public GeneralEmployee(String Firstname, String Lastname, String EmailAddress, String MobileNumber, String DateOfBirth, String JobTitle, double Salary) {
    super(Firstname, Lastname, EmailAddress, MobileNumber);
    this.setDateOfBirth(DateOfBirth);
    this.setJobTitle(JobTitle);
    this.setSalary(Salary);
  }

  public void setDateOfBirth(String DateOfBirth) {
    // if (DateOfBirth == null || DateOfBirth.trim().isEmpty()) {
    //    throw new IllegalArgumentException("DateOfBirth is null or blank");
    // }
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

  
  public void setSalary(double Salary) {
    this.Salary = Salary;
  }
  
  public double getSalary() {
    return this.Salary;
  }
  
  
}
