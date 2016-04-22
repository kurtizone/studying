package org.xerces.parsing.example.model;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class CarsErrorHandler implements ErrorHandler {

	public void warning(SAXParseException exception) throws SAXException {
		System.err.println(exception.getMessage());
	}

	public void error(SAXParseException exception) throws SAXException {
		System.err.println(exception.getMessage());
	}

	public void fatalError(SAXParseException exception) throws SAXException {
		System.err.println(exception.getMessage());
		throw exception;
	}

}
