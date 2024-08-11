package framework.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import project.models.Book;
import project.models.Catalog;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser {
    private static String url = PropertiesReader.readValueFromProperties(PropertiesReader.apiProperties, "url");
    public Catalog xmlParse() {
        Catalog catalog = new Catalog();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(url);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("book");
            List<Book> books = new ArrayList<>();
            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Book book = new Book(
                            elem.getAttribute("id"),
                            elem.getElementsByTagName("author").item(0).getTextContent(),
                            elem.getElementsByTagName("title").item(0).getTextContent(),
                            elem.getElementsByTagName("genre").item(0).getTextContent(),
                            elem.getElementsByTagName("price").item(0).getTextContent(),
                            elem.getElementsByTagName("publish_date").item(0).getTextContent(),
                            elem.getElementsByTagName("description").item(0).getTextContent()
                            );
                    books.add(book);
                }
            }
            catalog.setBooks(books);

        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return catalog;
    }
}
