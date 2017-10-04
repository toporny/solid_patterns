package solid_patterns;

public class GeneralEmployee extends Person implements IGeneralEmployee {
	  private String DateOfBirth;
	  private String JobTitle;
	  private String Salary;

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

	  
	  public void setSalary(String Salary) {
	    this.Salary = Salary;
	  }
	  
	  public String getSalary() {
	    return this.Salary;
	  }
	  
	  
	}
