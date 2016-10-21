package by.epam.tc.parsers.controller;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import org.xml.sax.SAXException;
import by.epam.tc.parsers.dom.DOMParser;
import by.epam.tc.parsers.sax.*;
import by.epam.tc.parsers.stax.STAXParser;
/*
 * created by Aliaksei Boltak on 21.10.2016
 */

public class Main {

	public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, XMLStreamException {

		// sax parser runner
		System.out.println("++++++++++++++++++++++++++++");
		System.out.println("SAX PARSER:");
		UserHandler sax =new UserHandler();
		sax.start("src/students.xml");
		System.out.println("++++++++++++++++++++++++++++");

		// dom parser runner
		System.out.println("DOM PARSER:");
		DOMParser dom = new DOMParser();
		dom.start("src/students.xml");
		System.out.println("++++++++++++++++++++++++++++");
		
		// stax parser runner
		System.out.println("STAX PARSER:");
		STAXParser stax = new STAXParser ();
		stax.start("src/students.xml");
		System.out.println("++++++++++++++++++++++++++++");
	}
}
