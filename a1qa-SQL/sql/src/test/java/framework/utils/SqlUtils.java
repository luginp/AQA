package framework.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;

public class SqlUtils {
    private static Logger logger = AqualityServices.getLogger();
    private static String url = PropertiesReader.getProperty(PropertiesReader.sqlProperties, "url");
    private static String user = PropertiesReader.getProperty(PropertiesReader.userProperties, "user");
    private static String password = PropertiesReader.getProperty(PropertiesReader.userProperties, "password");
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    public static void executeQuery(String query) {
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException sqlEx) {
            logger.fatal("Sql exception was caught", sqlEx);
        }
    }

    public static void parseQuery() {
        try {
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metadata.getColumnName(i);
                System.out.print(columnName + StringUtils.repeat(' ', 150) + "||");
            }
            System.out.println();
            System.out.println(StringUtils.repeat('_', 500));
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String project = rs.getString(i);
                    if (rs.wasNull()) {
                        project = "null";
                    }
                    System.out.print(project + StringUtils.repeat(' ', 150 + metadata.getColumnName(i).length() - project.length()) + "||");
                }
                System.out.println();
            }
            System.out.println();
        } catch (SQLException sqlEx) {
            logger.fatal("Sql exception was caught", sqlEx);
        }
    }

    public static void closeConnection() {
        try {
            con.close();
            stmt.close();
            rs.close();
        } catch (SQLException sqlException) {
            logger.fatal("Sql exception was caught", sqlException);
        }

    }
}
