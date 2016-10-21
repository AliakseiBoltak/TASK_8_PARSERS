package by.epam.tc.parsers.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserHandler extends DefaultHandler {

	boolean firstNameFound = false;
	boolean lastNameFound = false;
	boolean averageMarkFound = false;
	
	public void start(String path) throws IOException , SAXException, ParserConfigurationException  {
		
	try {
		File input = new File(path);
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		UserHandler userhandler = new UserHandler();
		saxParser.parse(input, userhandler);
	} catch (IOException | ParserConfigurationException |SAXException e) {
		e.printStackTrace();
		throw e;
	}
}
	
	@Override
	public void startElement(String uri, String localName, String qurrentName, Attributes attributes) throws SAXException {
		if (qurrentName.equalsIgnoreCase("student")) {
			String id = attributes.getValue("ID");
			System.out.println("ID : " + id);
		} else if (qurrentName.equalsIgnoreCase("firstname")) {
			firstNameFound = true;
		} else if (qurrentName.equalsIgnoreCase("lastname")) {
			lastNameFound= true;
		} else if (qurrentName.equalsIgnoreCase("averageMark")) {
			averageMarkFound = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qurrentName) throws SAXException {
		if (qurrentName.equalsIgnoreCase("student")) {
			System.out.println("END_ELEMENT:" + qurrentName);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (firstNameFound) {
			System.out.println("FIRST_NAME: " + new String(ch, start, length));
			firstNameFound = false;
		} else if (lastNameFound) {
			System.out.println("LAST_NAME: " + new String(ch, start, length));
			lastNameFound = false;
		} else if (averageMarkFound) {
			System.out.println("AVERAGE_MARK: " + new String(ch, start, length));
			averageMarkFound= false;
		}
	}
}