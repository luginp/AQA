package framework.utils;

import kong.unirest.Unirest;
import org.json.JSONObject;
import project.models.ApiResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import aquality.selenium.logger.Logger;

public class TestRailApi {
    private static Logger logger = Logger.getInstance();
    private static String url = PropertiesReader.readValueFromProperties(PropertiesReader.apiProperties, "url");
    private static String user = PropertiesReader.readValueFromProperties(PropertiesReader.userProperties, "user");
    private static String password = PropertiesReader.readValueFromProperties(PropertiesReader.userProperties, "password");

    private static String urlBuilder() {
        return url;
    }

    private static String getAuthorization() {
        return new String(Base64.getEncoder().encode((user + ":" + password).getBytes()));
    }

    public static ApiResponse sendRequest(String method, Map<String, String> param) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            URL url = new URL(urlBuilder() + method);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String auth = getAuthorization();
            connection.addRequestProperty("Authorization", "Basic " + auth);
            connection.addRequestProperty("Content-Type", "application/json");
            byte[] block = JSONObject.valueToString(param).getBytes(StandardCharsets.UTF_8);
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(block);
            outputStream.close();
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            apiResponse.setResponse(sb.toString());
        } catch (IOException e) {
            logger.fatal("Exception was caught", e);
        }
        return apiResponse;
    }

    public static void uniRestApi(String method, String filePath) {
        System.out.println(Unirest.post(urlBuilder() + method)
                .basicAuth(user, password)
                .field("attachment", new File(filePath))
                .queryString("port", "443")
                .asString()
                .getBody());
    }


    public static void sendRequestWithEmptyBody(String method) {
        try {
            URL url = new URL(urlBuilder() + method);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String auth = getAuthorization();
            connection.addRequestProperty("Authorization", "Basic " + auth);
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.getResponseMessage();
        } catch (IOException e) {
            logger.fatal("Exception was caught", e);
        }
    }
}
