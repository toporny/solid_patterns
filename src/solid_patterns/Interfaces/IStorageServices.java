package solid_patterns.Interfaces;
import java.util.ArrayList;
import java.util.Map;

import solid_patterns.Person;

public interface IStorageServices {

	public void saveOneRecord(Map<String, String> map);
	public void saveManyRecords(ArrayList<Person> people_list);
	public void close();
	
}
