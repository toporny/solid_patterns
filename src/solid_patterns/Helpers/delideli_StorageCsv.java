package solid_patterns.Helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class delideli_StorageCsv {
    public void saveData() 
    {
    	PrintWriter pw = null;
    	try {
    	    pw = new PrintWriter(new File("NewData.csv"));
    	} catch (FileNotFoundException e) {
    	    e.printStackTrace();
    	}
    	
        StringBuilder sb = new StringBuilder();
        sb.append("id");
        sb.append(',');
        sb.append("Name");
        sb.append('\n');

        sb.append("1");
        sb.append(',');
        sb.append("Prashant Ghimire");
        sb.append('\n');

        pw.write(sb.toString());
        pw.close();
        System.out.println("done!");
    }
}
