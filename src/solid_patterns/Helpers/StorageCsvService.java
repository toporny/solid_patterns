package solid_patterns.Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

import solid_patterns.Person;
import solid_patterns.Interfaces.IStorageServices;

public class StorageCsvService implements IStorageServices{

	private static String csvFileName = "NewData.csv";
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
			    System.out.println(s); 
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

 

}
