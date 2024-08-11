package framework.utils;


import framework.logger.Log;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class ReadValuesFromConfig {
    private static String neededText;
    public final static String globalPropertiesFileName = "properties.xml";
    public final  static String testPropertiesFileName = "testProperties.xml";
    private static String language = ReadValuesFromConfig.readValues("language", globalPropertiesFileName);

    public static String readValues(String neededValue, String fileName) {
        try {
            File fXmlFile = new File(fileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("properties");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    neededText = eElement.getElementsByTagName(neededValue).item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            Log.exceptionCatch("Catched exeption", e);
        }
        return neededText;
    }

    public static String readVocabularyValues(String neededValue) {
        try {
            File fXmlFile = new File("vocabulary.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList List = doc.getElementsByTagName(language);
            for (int temp = 0; temp < List.getLength(); temp++) {
                Node nNode = List.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    neededText = eElement.getElementsByTagName(neededValue).item(0).getTextContent();
                }
            }
        } catch (Exception e) {
            Log.exceptionCatch("Catched exeption", e);
        }
        return neededText;
    }
}

