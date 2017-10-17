package solid_patterns.Helpers;

import java.util.ArrayList;

import solid_patterns.Person;

public class Helper {

	public static ArrayList<Person> skipFirstOne(ArrayList<Person> people_list) {
		ArrayList<Person> copy = new ArrayList<Person>(people_list); 
		copy.remove(0);
		return copy;
		// I had to make copy of array list because ".remove(0)" deals on reference
		// Ayway.. this helper is only temporary function to show my saveOneRecord() and saveManyRecord()  
		
	}

}
