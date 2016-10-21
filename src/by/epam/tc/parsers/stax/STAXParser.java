package by.epam.tc.parsers.stax;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class STAXParser {

	boolean firstNameFound = false;
	boolean lastNameFound = false;
	boolean averageMarkFound = false;

	public void start(String path) throws IOException, XMLStreamException {

		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(path));

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				switch (event.getEventType()) {
				case XMLStreamConstants.START_ELEMENT:
					StartElement startElement = event.asStartElement();
					String qurrentName = startElement.getName().getLocalPart();
					if (qurrentName.equalsIgnoreCase("student")) {
						System.out.println("START_ELEMENT : student");
						Iterator<Attribute> attributes = startElement.getAttributes();
						String id = attributes.next().getValue();
						System.out.println("ID: " + id);
					} else if (qurrentName.equalsIgnoreCase("firstname")) {
						firstNameFound = true;
					} else if (qurrentName.equalsIgnoreCase("lastname")) {
						lastNameFound = true;
					} else if (qurrentName.equalsIgnoreCase("averageMark")) {
						averageMarkFound = true;
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					Characters characters = event.asCharacters();
					if (firstNameFound) {
						System.out.println("First Name: " + characters.getData());
						firstNameFound = false;
					}
					if (lastNameFound) {
						System.out.println("Last Name: " + characters.getData());
						lastNameFound = false;
					}
					if (averageMarkFound) {
						System.out.println("AVERAGE_MARK: " + characters.getData());
						averageMarkFound = false;
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					EndElement endElement = event.asEndElement();
					if (endElement.getName().getLocalPart().equalsIgnoreCase("student")) {
						System.out.println("END_ELEMENT : student");
						System.out.println("----------------------------");
					}
					break;
				}
			}
		} catch (IOException | XMLStreamException e ) {
			e.printStackTrace();
			throw e;

		}
	}
}
