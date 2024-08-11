package kaspersky.pageobject.utils;

import framework.logger.Log;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.*;

public class CheckingMails {
    private static String content;
    private static final String HOST = "imap.gmail.com";
    private static final String PROTOCOL = "imaps";
    private static final String FOLDER = "inbox";

    static Boolean readMessage(String email, String password, String productMaker, String os, String product) {
        try {
            Session session = Session.getDefaultInstance(imapsProperties());
            Store store = session.getStore(PROTOCOL);
            store.connect(HOST, email, password);
            Folder inbox = store.getFolder(FOLDER);
            inbox.open(Folder.READ_ONLY);
            for (Message message : inbox.getMessages()) {
                Object obj = message.getContent();
                Multipart mp = (Multipart) obj;
                BodyPart bp = mp.getBodyPart(0);
                if (message.getSubject().contains(productMaker) && parseForElement(bp.getContent().toString(), ProgramSelectId.getOsId(os)) && parseForElement(bp.getContent().toString(), ProgramSelectId.getProductId(os, product))) {
                    content = bp.getContent().toString();
                    return true;
                }
            }
            inbox.close(true);
            store.close();
        } catch (Exception e) {
            Log.exceptionCatch("Caught exception during message read", e);
        }
        return false;
    }

    private static Properties imapsProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.imaps.host", "imap.gmail.com");
        properties.setProperty("mail.imaps.port", "993");
        properties.setProperty("mail.imaps.connectiontimeout", "5000");
        properties.setProperty("mail.imaps.timeout", "5000");
        return properties;
    }

    private static boolean parseForElement(String text, String elementText) {
        Pattern pattern = Pattern.compile(elementText);
        String part = null;
        boolean bool = false;
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            part = text.substring(matcher.start(), matcher.end());
        }
        if (part != null)
            bool = true;
        return bool;
    }

    public static boolean emailHasRightLink(String os, String product) {
        return parseForElement(content, ProgramSelectId.getOsId(os)) && parseForElement(content, ProgramSelectId.getProductId(os, product));
    }
}


