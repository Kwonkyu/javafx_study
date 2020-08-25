package application;

import java.util.Map;
import java.util.HashMap;

public class Dictionary {
	private final static String NO_KEY_VALUE = "No Registered Value";
	private final Map<String, String> dictionary;
	
	public Dictionary() {
		dictionary = new HashMap<>();
		// hard coded dictionary data.
		dictionary.put("Bob Dylan", "Blowin' in the Wind");
		dictionary.put("Pete Seeger", "We Shall Overcome");
		dictionary.put("Woody Guthrie", "This Land is Your Land");
	}

	public String get(String text) {
		return dictionary.getOrDefault(text, NO_KEY_VALUE);
	}
}
