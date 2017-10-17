package solid_patterns.Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

import solid_patterns.Contractor;
import solid_patterns.GeneralEmployee;
import solid_patterns.Guest;
import solid_patterns.Person;
import solid_patterns.Interfaces.IStorageServices;

public class StorageCsvService implements IStorageServices{

	private static String csvFileName = "D16127504.csv";
	private String[] csvStructure = {"role", "firstname", "lastname", "email_address", "mobile_number", "date_of_birth", "job_title", "salary", "contact", "company"};
	private File file;
	private PrintWriter pw = null;
	
	private static Logger LOGGER = Logger.getLogger("InfoLogging");
	
	/* 
	 * Storage CSV Service contructor. Checks if file exists. If not then create
	*/	
	public StorageCsvService() {
    	try {
    		file = new File(csvFileName);
    	    pw = new PrintWriter(new FileOutputStream(file, true));
    	} catch (FileNotFoundException e) {
    	    e.printStackTrace();
    	}

        StringBuilder sb = new StringBuilder();

    	if (file.length() == 0) {
    		LOGGER.info("Seems file "+csvFileName+" did not exist. Header required.");

    		// create header and add to CSV file as a first line:
			for (String s: csvStructure) {           
	            sb.append(s);
	            sb.append(';');
			}
            sb.append('\n');
			pw.write(sb.toString());
    	}
    	else {
    		LOGGER.info("Seems file "+csvFileName+" exists. No need to add Header to the top of the file...");
    	}
	}
	
	
	/*
	 * Save one record to CVS file.
	 */		
	public void saveOneRecord(Map<String, String> m) {
        StringBuilder sb = new StringBuilder();
		for (String s: csvStructure) {           
            if (m.get(s) != null) {
            	sb.append(m.get(s));
            }
            sb.append("; ");
		}
        sb.append("\n");
		pw.write(sb.toString());
	}

	
	/*
	 * Save many records to CVS file.
	 */	
	public void saveManyRecords(ArrayList<Person> people_list) {
		for (Object val : people_list) {
			Map<String, String> m = ((Person) val).getAllDetails();
	        StringBuilder sb = new StringBuilder();
			for (String s: csvStructure) {           
	            if (m.get(s) != null) {
	            	sb.append(m.get(s));
	            }
	            sb.append("; ");
			}
	        sb.append("\n");
			pw.write(sb.toString());
		}
	}
		

	
	/*
	 * Close File connection
	 */
	public void close() {
		pw.close();
	}
	
	
	
	/*
	 * Read all data from CSV file
	 */	
	public ArrayList<Person> readAllData() {

		ArrayList<Person> people_list = new ArrayList<Person>();
//		String tableFields = String.join(", ", aTableColumns);
//
//		GeneralEmployee gen ;
//		Contractor con ;
//		Guest gue ;
//
//		try {
//			ResultSet rs1 = stmt.executeQuery("SELECT " + tableFields + " FROM people");
//			while (rs1.next()) {
//				String role = rs1.getString("role");
//				switch (role) {
//				
//				case "GeneralEmployee":
//					gen  = new GeneralEmployee(rs1.getString("firstname"), rs1.getString("lastname"), rs1.getString("email_address"), rs1.getString("mobile_number"));
//					gen.setDateOfBirth(rs1.getString("date_of_birth"));
//					gen.setJobTitle(rs1.getString("job_title"));
//					gen.setSalary(rs1.getString("salary"));
//					people_list.add(gen);
//					break;
//
//				case "Contractor":
//					con  = new Contractor(rs1.getString("firstname"), rs1.getString("lastname"), rs1.getString("email_address"), rs1.getString("mobile_number"));
//					con.setDateOfBirth(rs1.getString("date_of_birth"));
//					con.setCompany(rs1.getString("company"));
//					con.setContact(rs1.getString("contact"));
//					people_list.add(con);
//					break;
//
//				case "Guest":
//					gue  = new Guest(rs1.getString("firstname"), rs1.getString("lastname"), rs1.getString("email_address"), rs1.getString("mobile_number"));
//					gue.setCompany(rs1.getString("company"));
//					gue.setContact(rs1.getString("contact"));
//					people_list.add(gue);
//					break;
//
//				default: // protection against unknown role from database
//					throw new IllegalArgumentException("Unknown role: " + role);
//
//				}
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return people_list;
	}

	

 

}
