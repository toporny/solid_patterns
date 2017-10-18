package d16127504.Interfaces;
import java.util.ArrayList;
import java.util.Map;

import d16127504.Person;

public interface IStorageServices {

	public void saveOneRecord(Map<String, String> map);
	public void saveManyRecords(ArrayList<Person> people_list);
	public ArrayList<Person> readAllData();
	public void close();
	
}
