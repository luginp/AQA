package project.tests;

import framework.utils.PropertiesReader;
import framework.utils.SqlUtils;
import org.testng.annotations.Test;

public class SqlTest {
    private String firstQuery = PropertiesReader.getProperty(PropertiesReader.sqlProperties, "firstQuery");
    private String secondQuery = PropertiesReader.getProperty(PropertiesReader.sqlProperties, "secondQuery");
    private String thirdQuery = PropertiesReader.getProperty(PropertiesReader.sqlProperties, "thirdQuery");
    private String forthQuery = PropertiesReader.getProperty(PropertiesReader.sqlProperties, "forthQuery");
    private String date = PropertiesReader.getProperty(PropertiesReader.testProperties, "date");
    private String browser1 = PropertiesReader.getProperty(PropertiesReader.testProperties, "browser1");
    private String browser2 = PropertiesReader.getProperty(PropertiesReader.testProperties, "browser2");


    @Test
    public void sqlTest(){
        SqlUtils.executeQuery(firstQuery);
        SqlUtils.parseQuery();
        SqlUtils.executeQuery(secondQuery);
        SqlUtils.parseQuery();
        SqlUtils.executeQuery(String.format(thirdQuery,date));
        SqlUtils.parseQuery();
        SqlUtils.executeQuery(String.format(forthQuery, browser1, browser2));
        SqlUtils.parseQuery();
        SqlUtils.closeConnection();
    }
}
