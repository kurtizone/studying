package org.server.prop;

import java.util.Map;
import java.util.Properties;

import org.server.main.RMIServerSide;

public class ConfigProp {
	
	public static Properties prop  = new Properties();
	
	public ConfigProp() {
		prop = System.getProperties();
		if(RMIServerSide.LOAD_ON_START) {
			prop.put("prop.loadonstart", "true");
		} else 	prop.put("prop.loadonstart", "false");
	}
	
	public static void printProp() {
		System.out.println("Properties:");
		for (Map.Entry<Object, Object> entry : prop.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}
	
}
