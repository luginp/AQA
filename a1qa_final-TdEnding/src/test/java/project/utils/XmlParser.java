package project.utils;

import aquality.selenium.browser.AqualityServices;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import project.models.Catalog;
import project.models.Test;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {

    public static Catalog xmlParse(String xmlText) {
        Catalog catalog = new Catalog();
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                    .parse(new InputSource(new StringReader("<?xml version=\"1.0\"?><catalog>" + xmlText + "</catalog>")));
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("test");
            List<Test> tests = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Test test = new Test();
                    test.setMethod(elem.getElementsByTagName("method").item(0).getTextContent());
                    test.setName(elem.getElementsByTagName("name").item(0).getTextContent());
                    test.setStartTime(elem.getElementsByTagName("startTime").item(0).getTextContent());
                    test.setStatus(elem.getElementsByTagName("status").item(0).getTextContent().toUpperCase());
                    if(elem.getElementsByTagName("status").item(0).getTextContent().contains("In progress")) {
                        test.setEndTime("");
                    } else {
                        test.setEndTime(elem.getElementsByTagName("endTime").item(0).getTextContent());
                    }
                    tests.add(test);
                }
            }
            catalog.setTests(tests);

        } catch (SAXException | ParserConfigurationException | IOException e) {
            AqualityServices.getLogger().fatal("Exception was caught", e);
        }
        return catalog;
    }
}
