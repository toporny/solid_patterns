package d16127504.Helpers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Logger;

import d16127504.Contractor;
import d16127504.GeneralEmployee;
import d16127504.Guest;
import d16127504.Person;
import d16127504.Interfaces.IStorageServices;

public class StorageDbService implements IStorageServices{

	private static String sqliteFileName = "jdbc:sqlite:D16127504.sqlite";
	private String[] aTableColumns = {"role", "firstname", "lastname", "email_address", "mobile_number", "date_of_birth", "job_title", "salary", "contact", "company"};

	private static Logger LOGGER = Logger.getLogger("InfoLogging");

	Connection c = null;
	Statement stmt = null;

	/* 
	 * Storage DB Service contructor. Checks if table exists. If not then create table
	 */
	public StorageDbService() {

		// try to connect to SQLite
		try {		
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(sqliteFileName);
			// System.out.println("Were connected ok!!");
			LOGGER.info("database "+sqliteFileName+" connected ok!!.");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ":"+e.getMessage());
			System.exit(0);
		}

		// check if table exists. If not then create table 
		try {
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT count(*) as total FROM sqlite_master WHERE type='table' AND name='people'");

			// if there is no /PEOPLE/ table in databaase then create it.
			if (rs.getInt("total") == 0) {

				LOGGER.info("Seems table /people/ not exists. I need to create it...");

				stmt = c.createStatement();
				String createTable = "CREATE TABLE people "+
						" (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
						" role CHAR(16) NOT NULL, "+
						" firstname CHAR(80) NOT NULL, "+
						" lastname CHAR(80) NOT NULL, "+
						" email_address CHAR(80) NOT NULL, "+
						" mobile_number CHAR(80) NOT NULL, "+  // lets set CHAR because 'plus' on the beginning: "+353 861 055 543" 
						" date_of_birth DATE, "+ 
						" job_title CHAR(50), "+ 
						" Salary CHAR(10), "+  // lets set CHAR because 'currency can be added' on the beginning: "1200$ or €"
						" contact CHAR(50), "+
						" company CHAR(50) "+
						")";
				stmt.executeUpdate(createTable);

			} else {
				LOGGER.info("Seems table /people/ already exist.");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getClass().getName() + ":"+e.getMessage());
			e.printStackTrace();
		}
	}


	/* 
	 * all column names are stored in /this.aTableColumns/ array
	 * now I have to build /aTableValues/ with coresponding values
	 * note:
	 * "General_Employee" has "Date_of_Birth" but doesn't have "Contact" property
	 * but "Contractor" has "Contact" but doesn't have "Date_of_Birth".. etc 
	 */

	private String[] PrepareValuesArray(Map<String, String> m){
		String[] aTableValues = new String[aTableColumns.length];
		for (int i=0; i<aTableColumns.length; i++) {
			if (m.get(aTableColumns[i]) != null) 
				aTableValues[i] = "'" + m.get(aTableColumns[i]) + "'";
			else 
				aTableValues[i] = "''"; // set empty string if field does not exist.
		}		
		return aTableValues;
	}		


	/*
	 * Save one record into DB
	 */
	public void saveOneRecord(Map<String, String> m) {

		// prepare array with values
		String[] aTableValues = PrepareValuesArray(m);

		// building query
		String tableFields = String.join(", ", aTableColumns);
		String tableValues = String.join(", ", aTableValues);
		String finalQuery = "INSERT INTO people (" + tableFields + ") " + "VALUES (" + tableValues + ")";

		try {
			stmt.executeUpdate(finalQuery);
		} catch (SQLException e) {
			LOGGER.info("Problem with inserting one row to database");
			LOGGER.info("final query:" + finalQuery);
			LOGGER.info(e.getClass().getName() + ":" + e.getMessage());
			e.printStackTrace();
		}
	}


	/*
	 * Save many records into database.
	 * this is inefficient way because it calls saveOneRecord() many times
	 * instead build one big insert query and sebd it by one "execute" command.
	 * however maximum number of bytes in the text of an SQL statement is limited
	 * to SQLITE_MAX_SQL_LENGTH which defaults to 1000000 bytes. (depends of OS)
	 */
	public void saveManyRecords(ArrayList<Person> people_list) {
		for (Object val : people_list) {
			Map<String, String> m = ((Person) val).getAllDetails();
			this.saveOneRecord(m);
		}
	}


	/*
	 * Read all data from DB
	 */	
	public ArrayList<Person> readAllData() {

		String tableFields = String.join(", ", aTableColumns);
		ArrayList<Person> people_list = new ArrayList<Person>();
		GeneralEmployee gen;
		Contractor con;
		Guest gue;

		try {
			ResultSet rs1 = stmt.executeQuery("SELECT " + tableFields + " FROM people");
			while (rs1.next()) {
				String role = rs1.getString("role");
				switch (role) {
				
				case "GeneralEmployee":
					gen  = new GeneralEmployee(rs1.getString("firstname"), rs1.getString("lastname"), rs1.getString("email_address"), rs1.getString("mobile_number"));
					gen.setDateOfBirth(rs1.getString("date_of_birth"));
					gen.setJobTitle(rs1.getString("job_title"));
					gen.setSalary(rs1.getString("salary"));
					people_list.add(gen);
					break;

				case "Contractor":
					con  = new Contractor(rs1.getString("firstname"), rs1.getString("lastname"), rs1.getString("email_address"), rs1.getString("mobile_number"));
					con.setDateOfBirth(rs1.getString("date_of_birth"));
					con.setCompany(rs1.getString("company"));
					con.setContact(rs1.getString("contact"));
					people_list.add(con);
					break;

				case "Guest":
					gue  = new Guest(rs1.getString("firstname"), rs1.getString("lastname"), rs1.getString("email_address"), rs1.getString("mobile_number"));
					gue.setCompany(rs1.getString("company"));
					gue.setContact(rs1.getString("contact"));
					people_list.add(gue);
					break;

				default: // protection against unknown role from database
					throw new IllegalArgumentException("Unknown role: " + role);

				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return people_list;
	}


	
	/*
	 * Close DB connection
	 */
	public void close() {
		try {		
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOGGER.info("problem with closing database.");
			System.err.println(e.getClass().getName() + ":"+e.getMessage());
			e.printStackTrace();
		}
	}
	




}
