package framework.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import project.models.ApiResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiUtils {
    private static Logger logger = AqualityServices.getLogger();

    private static String url = PropertiesReader.readValueFromProperties(PropertiesReader.apiProperties, "url");

    private static String urlBuilder() {
        return url;
    }

    public static ApiResponse sendGetRequest() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            URL url = new URL(urlBuilder());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            StringBuilder sb = new StringBuilder();
            apiResponse.setStatusCode(connection.getResponseCode());
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
}
