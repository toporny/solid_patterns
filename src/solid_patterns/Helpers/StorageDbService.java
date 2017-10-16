package solid_patterns.Helpers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

import solid_patterns.Interfaces.IStorageServices;

public class StorageDbService implements IStorageServices{
	
	Connection c = null;
	Statement stmt = null;
	
	public void save(Map<String, String> map) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void openDbConn() {
		try {		
	    	Class.forName("org.sqlite.JDBC");
	    	c = DriverManager.getConnection("jdbc:sqlite:students4.sqlite");
	    	// System.out.println("Were connected ok!!");
	    	
	    } catch (Exception e) {
	    	System.err.println(e.getClass().getName() + ":"+e.getMessage());
	    	System.exit(0);
	    }
	}

	public void closeDbConn() {

		try {		
			c.close();
	    } catch (Exception e) {
	    	System.err.println(e.getClass().getName() + ":"+e.getMessage());
	    	System.exit(0);
	    }		
	}

 	
// https://www.tutorialspoint.com/sqlite/sqlite_java.htm
	
	public void connect2 () {
		try {

	    	stmt = c.createStatement();
	    	String createTable = "CREATE TABLE Students4 "+
	    			" (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
	    			" firstname CHAR(80) NOT NULL, "+
	    			" lastname CHAR(80) NOT NULL, "+
	    			" coursename CHAR(80) NOT NULL, "+
	    			" age INT NOT NULL, "+
	    			" address CHAR(50) "+
	    		")";
	    	stmt.executeUpdate(createTable);
	    	
	    	String insertStudent = "INSERT INTO Students4 (firstname, lastname, coursename, age, address) "
	    	+ "VALUES ('Barry','Allen','Higher Diploma',21,'Dublin');";
	    	
	    	stmt.executeUpdate(insertStudent);
	    	
	    	String updateStudent = "UPDATE Students4 set age = 27 where ID = 1;";
	    	stmt.executeUpdate(updateStudent);
	    	    	    	
	    	ResultSet rs = stmt.executeQuery("SELECT * FROM Students4");
	    	
	    	while (rs.next()) {
	    		int id = rs.getInt("id");
	    		String firstname = rs.getString("firstname");
	    		String lastname = rs.getString("lastname");
	    		String coursename = rs.getString("coursename");
	    		int age = rs.getInt("age");
	    		String address = rs.getString("address");
	    		
	    		System.out.println("rwer");
	    		System.out.println("id: " + id);
	    		System.out.println("firstname: " + firstname);
	    		System.out.println("lastname: " + lastname);
	    		System.out.println("coursename: " + coursename);
	    		System.out.println("age: " + age);
	    		System.out.println("address: " + address);
	    		System.out.println();
	    		
	    		
	    	}
	
	    	
	    	c.close();
	    	
	    } catch (Exception e) {
	    	System.err.println(e.getClass().getName() + ":"+e.getMessage());
	    	System.exit(0);
	    }
    		
	}


}
