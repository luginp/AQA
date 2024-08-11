package framework.utils;

import aquality.selenium.logger.Logger;
import project.models.Catalog;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

public class XmlUtils {
    private static Logger logger = Logger.getInstance();
    public static Catalog xmlStringToObject(String xmlString) {
        JAXBContext jaxbContext;
        Catalog catalog = null;
        try {
            jaxbContext = JAXBContext.newInstance(Catalog.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            catalog = (Catalog) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
        } catch (JAXBException e) {
            logger.fatal("Exception catched", e);
        }
        return catalog;
    }

}
