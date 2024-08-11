package framework.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import kong.unirest.Unirest;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import project.models.ApiResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class ApiUtils {
    private static Logger logger = AqualityServices.getLogger();
    private static String url = PropertiesReader.readValueFromProperties(PropertiesReader.apiProperties, "url");
    private static String user = PropertiesReader.readValueFromProperties(PropertiesReader.userProperties, "user");
    private static String password = PropertiesReader.readValueFromProperties(PropertiesReader.userProperties, "password");

    private static String urlBuilder() {
        return url;
    }

    private static String getAuthorization() {
        return new String(Base64.getEncoder().encode((user + ":" + password).getBytes()));
    }

    public static ApiResponse sendBasicAuthRequest() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            URL url = new URL(urlBuilder());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", "Basic " + getAuthorization());
            connection.addRequestProperty("Content-Type", "application/json");
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

    public static int userAuthentication() {
        CredentialsProvider provider = new BasicCredentialsProvider();
        UsernamePasswordCredentials credentials
                = new UsernamePasswordCredentials(user, password);
        provider.setCredentials(AuthScope.ANY, credentials);

        HttpClient client = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(provider)
                .build();

        HttpResponse response = null;
        try {
            response = client.execute(
                    new HttpGet(urlBuilder()));
        } catch (IOException e) {
            logger.fatal("Exception was caught", e);
        }
        int statusCode = response.getStatusLine()
                .getStatusCode();

        return statusCode;
    }

    public static void sendRequestWithEmptyBody(String method) {
        try {
            URL url = new URL(urlBuilder() + method);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", "Basic " + getAuthorization());
            connection.addRequestProperty("Content-Type", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.getResponseMessage();
        } catch (IOException e) {
            logger.fatal("Exception was caught", e);
        }
    }
}
