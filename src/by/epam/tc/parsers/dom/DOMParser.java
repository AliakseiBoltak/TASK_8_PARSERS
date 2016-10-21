package by.epam.tc.parsers.dom;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMParser {

	boolean firstNameFound = false;
	boolean lastNameFound = false;
	boolean averageMarkFound = false;

	public void start(String path) throws ParserConfigurationException, SAXException {

		try {
			File input = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(input);
			doc.getDocumentElement().normalize();
			System.out.println("ROOT_ELEMENT :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("student");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("----------------------------");
				System.out.println("CURRENT_ELEMENT :" + nNode.getNodeName());
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					System.out.println("ID : " + eElement.getAttribute("ID"));
					System.out.println(
							"FIRST_NAME: " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
					System.out.println(
							"LAST_NAME: " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println(
							"AVERAGE_MARK : " + eElement.getElementsByTagName("averageMark").item(0).getTextContent());
				}
			}
		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
	}

}
