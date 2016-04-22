package org.xerces.parsing.example;

import org.xerces.parsing.example.model.Car;

public class Main {
	
	public final static String XML_PATH = "src/main/resources/cars.xml"; 
	
	public static void main(String[] args) {
		CarParser parser = new CarParser(XML_PATH);
		parser.parseCars();
		
		for (Car car : parser.getCars()) {
			System.out.println(car);
		}
	}
	
}
