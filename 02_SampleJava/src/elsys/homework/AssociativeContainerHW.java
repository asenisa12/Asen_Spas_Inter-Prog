package elsys.homework;

import java.util.HashMap;
import java.util.Map;

public class AssociativeContainerHW {
	private static final String KEY_GERMANY = "Germany";
	private static final String KEY_BULGARIA = "Bulgaria";
	private static final String KEY_GREECE = "Greece";
	private static final String KEY_NETHERLAND = "Netherland";
	private static final String KEY_RUSSIA = "Russia";
	private static final String KEY_FRANCE = "France";

	public static void main(String[] args) {
		final Map<String, Integer> m = new HashMap<String, Integer>();
		
		m.put(KEY_BULGARIA, 7);
		m.put(KEY_GERMANY, 80);
		m.put(KEY_GREECE, 7);
		m.put(KEY_NETHERLAND, 10);
		m.put(KEY_RUSSIA, 145);
		m.put(KEY_FRANCE, 60);
		
		for(String key : m.keySet()){
			if(m.get(key) > 10)
				System.out.println(key);
		}
	}

}
