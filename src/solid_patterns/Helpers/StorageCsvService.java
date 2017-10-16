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
	
	public StorageCsvService() {
    	try {
    		file = new File(csvFileName);
    	    pw = new PrintWriter(new FileOutputStream(new File(csvFileName), true));
    	} catch (FileNotFoundException e) {
    	    e.printStackTrace();
    	}

        StringBuilder sb = new StringBuilder();

    	if (file.length() == 0) {
    		LOGGER.info("Seems file "+csvFileName+" did not exist. Header required.");

    		// create header to add to CSV file:
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
	
	
	public void saveOne(Map<String, String> map) {
		// TODO Auto-generated method stub
	}

	public void saveMany() {
		// TODO Auto-generated method stub
	}

	public void close() {
		pw.close();
	}


	@Override
	public void saveMany(ArrayList<Person> people_list) {
		// TODO Auto-generated method stub
		
	}


}
