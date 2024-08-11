package framework.utils;

import aquality.selenium.browser.AqualityServices;
import org.json.JSONObject;
import project.models.ApiResponse;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class ApiUtils {
    private static String urlBuilder(String url, String method) {
        return url + method;
    }

    private static String getAuthorization(String user, String password) {
        return new String(Base64.getEncoder().encode((user + ":" + password).getBytes()));
    }

    public static ApiResponse makePostApiRequest(String uri, String method) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            URL obj = new URL(urlBuilder(uri, method));
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.addRequestProperty("Content-Type", "application/xml");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            OutputStream outputStream = connection.getOutputStream();
            outputStream.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            apiResponse.setResponse(response.toString());
        } catch (IOException e) {
            AqualityServices.getLogger().fatal("Exception was caught", e);
        }
        return apiResponse;
    }

    public static ApiResponse sendGetRequestWithBasicAuth(String uri,String user, String password, String method, Map<String, String> param) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            URL url = new URL(urlBuilder(uri, method));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", "Basic " + getAuthorization(user, password));
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
            AqualityServices.getLogger().fatal("Exception was caught", e);
        }
        return apiResponse;
    }

    public static ApiResponse sendBasicAuthRequest(String uri, String user, String password) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.addRequestProperty("Authorization", "Basic " + getAuthorization(user, password));
            connection.addRequestProperty("Content-Type", "application/json");
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            apiResponse.setResponse(sb.toString());
        } catch (IOException e) {
            AqualityServices.getLogger().fatal("Exception was caught", e);
        }
        return apiResponse;
    }

    public static void sendFilePostRequestWithBasicAuth(String uri,String user, String password, String method, String filePath) {
        try {
            URL url = new URL(urlBuilder(uri, method));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("Authorization", "Basic " + getAuthorization(user, password));
            conn.setRequestMethod("POST");
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

        } catch (IOException e) {
            AqualityServices.getLogger().fatal("Exception was caught", e);
        }
    }
}

