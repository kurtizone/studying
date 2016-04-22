package org.server.service;

import java.util.Map;

public interface PropTableAct {
	
	Map<String, String> getAllProperties();
	boolean updateValue(String entryKey, String entryValue);
	boolean commitChanges();

}
