package solid_patterns.Helpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

import solid_d16127504.Contractor;
import solid_d16127504.GeneralEmployee;
import solid_d16127504.Guest;
import solid_d16127504.Person;
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
	            sb.append(";");
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
	 * CSV parser - Reads and parses all rows from CSV file
	 */	
	public ArrayList<Person> readAllData() {

		ArrayList<Person> people_list = new ArrayList<Person>();
		GeneralEmployee gen;
		Contractor con;
		Guest gue;
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFileName))) {
		    String line = br.readLine();
		    line = br.readLine(); // ignore first line (with header) by just make another readLine()
		    
			// csvStructure = {"role", "firstname", "lastname", "email_address", "mobile_number", "date_of_birth", "job_title", "salary", "contact", "company"};
		    while (line != null) {
		        line = br.readLine();
		        if (line != null) {
		        	String a[] = line.split(";");
		        	String role = a[0];

		        	switch (role) {
					case "GeneralEmployee":
						gen = new GeneralEmployee(a[1],a[2],a[3],a[4]); // indexes are hardcoded
						gen.setDateOfBirth(a[5]);
						gen.setJobTitle(a[6]);
						gen.setSalary(a[7]);
						people_list.add(gen);
						break;

					case "Contractor":
						con = new Contractor(a[1],a[2],a[3],a[4]);
						con.setDateOfBirth(a[5]);
						con.setCompany(a[8]);
						con.setContact(a[9]);
						people_list.add(con);
						break;

					case "Guest":
						gue  = new Guest(a[1],a[2],a[3],a[4]);
						gue.setCompany(a[8]);
						gue.setContact(a[9]);
						people_list.add(gue);
						break;

					default: // protection against unknown role from database
						throw new IllegalArgumentException("Unknown role: " + role);

		        	}
		        }
		        
		    }
		    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return people_list;
	}

}
