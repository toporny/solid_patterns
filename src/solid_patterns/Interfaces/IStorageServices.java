package solid_patterns.Interfaces;
import java.util.ArrayList;
import java.util.Map;

import solid_patterns.Person;

public interface IStorageServices {

	public void saveOne(Map<String, String> map);
	
	public void saveMany(ArrayList<Person> people_list);
	

	public void close();
}
