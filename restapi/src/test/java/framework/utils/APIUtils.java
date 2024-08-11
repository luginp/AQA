package framework.utils;

import aquality.selenium.logger.Logger;
import project.models.ApiResponse;

import java.io.IOException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIUtils {
    private static Logger logger = Logger.getInstance();

    public static ApiResponse makeGetApiRequest(String url) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            apiResponse.setStatusCode(connection.getResponseCode());
            InputStream inputStream = connection.getInputStream();
            byte[] res = new byte[2048];
            int i = 0;
            StringBuilder response = new StringBuilder();
            while ((i = inputStream.read(res)) != -1) {
                response.append(new String(res, 0, i));
            }
            apiResponse.setResponse(response.toString());
            inputStream.close();
        } catch (IOException e) {
            logger.fatal("Exception was caught", e);
        }
        return apiResponse;
    }

    public static ApiResponse makePostApiRequest(String url) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoOutput(true);
            final DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.flush();
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            apiResponse.setStatusCode(connection.getResponseCode());
            apiResponse.setResponse(response.toString());
        } catch (IOException e) {
            logger.fatal("Exception was caught", e);
        }
        return apiResponse;
    }

}
