package org.xerces.parsing.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xerces.parsing.example.model.Car;
import org.xerces.parsing.example.model.CarsErrorHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CarParser {
	private List<Car> cars;
	private Document doc;
	private String path;
	
	public CarParser(String path) {
		this.path = path;
		cars = new ArrayList<Car>();
	}
	
	public void parseCars() {
		parseXmlFile();
		parseDocument();
	}
	
	private void parseXmlFile() {
		try {
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			File schemaLocation = new File("src/main/resources/cars.xsd");
			Schema schema = sf.newSchema(schemaLocation);
			Validator validator = schema.newValidator();
			Source source = new StreamSource(path);
			validator.setErrorHandler(new CarsErrorHandler());
			validator.validate(source);
			System.out.println("XML File is valid");
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setNamespaceAware(true);
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			db.setEntityResolver(new EntityResolver() {
				
				public InputSource resolveEntity(String publicId, String systemId) throws SAXException, IOException {
					if(systemId.equals("http://www.w3schools.com/Kurtizone")) {
						return new InputSource("src/main/resources/cars.xsd");
					}
					return null;
				}
			});
			doc = db.parse(path);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	private void parseDocument() {
		Element docElem = doc.getDocumentElement();
		NodeList nodeList = docElem.getElementsByTagName("car");
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				
				Element el = (Element) nodeList.item(i);
				
				Car car = getCar(el);
				
				cars.add(car);
				
			}
		}
	}

	private Car getCar(Element el) {
		return new Car(
				Integer.parseInt(el.getAttribute("id")),
				getTextValue(el, "manufacturer"),
				getTextValue(el, "model"),
				getIntegerValue(el, "weight"),
				getIntegerValue(el, "maxSpeed"),
				getTextValue(el, "color"),
				getIntegerValue(el, "price"));
	}
	
	private String getTextValue(Element el, String tagName) {
		String textVal = null;
		NodeList nodeList = el.getElementsByTagName(tagName);
		if(nodeList != null && nodeList.getLength() > 0) {
			Element ele =(Element) nodeList.item(0);
			textVal = ele.getFirstChild().getNodeValue();
		}
		return textVal;
	}
	
	private Integer getIntegerValue(Element el, String tagName) {
		return Integer.parseInt(getTextValue(el, tagName));
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	
	
}
