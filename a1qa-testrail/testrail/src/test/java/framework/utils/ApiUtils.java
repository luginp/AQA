package framework.utils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import kong.unirest.Unirest;
import net.minidev.json.JSONValue;
import org.json.JSONObject;
import project.models.ApiResponse;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Map;

public class ApiUtils {
    private static Logger logger = AqualityServices.getLogger();
    private static String url = PropertiesReader.getProperty(PropertiesReader.apiProperties, "url");
    private static String user = PropertiesReader.getProperty(PropertiesReader.userProperties, "user");
    private static String password = PropertiesReader.getProperty(PropertiesReader.userProperties, "password");

    private static String urlBuilder(String method) {
        return url + method;
    }

    private static String getAuthorization() {
        return new String(Base64.getEncoder().encode((user + ":" + password).getBytes()));
    }

    public static ApiResponse sendGetRequest(String method, Map<String, String> param) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            URL url = new URL(urlBuilder(method));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", "Basic " + getAuthorization());
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

    public static ApiResponse sendPostFileRequest(String method, String filePath) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            URL url = new URL(urlBuilder(method));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", "Basic " + getAuthorization());
            connection.addRequestProperty("Content-Type", "multipart/form-data");
            connection.setDoOutput(true);
            OutputStream output = connection.getOutputStream();
            Files.copy(new File(filePath).toPath(), output);
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String outputString;
            while ((outputString = br.readLine()) != null) {
                sb.append(outputString);
            }
            apiResponse.setResponse(sb.toString());
        } catch (IOException e) {
            logger.fatal("Exception was caught", e);
        }
        return apiResponse;
    }

    public static void postWithFileRequest(String method, String filePath) {
        Unirest.post(urlBuilder(method))
                .basicAuth(user, password)
                .field("upload", new File(filePath))
                .queryString("port", "443")
                .asString()
                .getBody();
    }


    public static void sendRequestWithEmptyBody(String method) {
        try {
            URL url = new URL(urlBuilder(method));
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

    public static void sendPostRequest(String uri, String filePath) {
        try {
            URL url = new URL(urlBuilder(uri));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("Authorization", "Basic " + getAuthorization());
            conn.setRequestMethod("POST");
            if (uri.startsWith("add_attachment"))   // add_attachment API requests
            {
                String boundary = "TestRailAPIAttachmentBoundary"; //Can be any random string
                File uploadFile = new File(filePath);
                conn.setDoOutput(true);
                conn.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
                OutputStream ostreamBody = conn.getOutputStream();
                BufferedWriter bodyWriter = new BufferedWriter(new OutputStreamWriter(ostreamBody));
                bodyWriter.write("\n\n--" + boundary + "\r\n");
                bodyWriter.write("Content-Disposition: form-data; name=\"attachment\"; filename=\""
                        + uploadFile.getName() + "\"");
                bodyWriter.write("\r\n\r\n");
                bodyWriter.flush();
                //Read file into request
                InputStream istreamFile = new FileInputStream(uploadFile);
                int bytesRead;
                byte[] dataBuffer = new byte[1024];
                while ((bytesRead = istreamFile.read(dataBuffer)) != -1) {
                    ostreamBody.write(dataBuffer, 0, bytesRead);
                }
                ostreamBody.flush();

                //end of attachment, add boundary
                bodyWriter.write("\r\n--" + boundary + "--\r\n");
                bodyWriter.flush();

                //Close streams
                istreamFile.close();
                ostreamBody.close();
                bodyWriter.close();
            }
        } catch (IOException e) {
            logger.fatal("Exception was caught", e);
        }
    }
}

