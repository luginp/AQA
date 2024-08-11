package framework.utils;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import framework.logger.Log;
import net.minidev.json.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class ApiUtils {

    private static String responseText;

    public static void makePostApiRequest(String url) {
        Log.info("Making Post api request");
        try {
            URL obj = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection) obj.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(httpConn.getOutputStream());
            wr.flush();
            wr.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            responseText = response.toString();
        } catch (IOException e) {
            Log.exceptionCatch("Exception thrown", e);
        }

    }

    public static void uploadFile(String url, String fileName) {
        Log.info("Making Post api request to upload file");
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost uploadFile = new HttpPost(url);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
            File f = new File(fileName);
            builder.addBinaryBody(
                    "file",
                    new FileInputStream(f),
                    ContentType.APPLICATION_OCTET_STREAM,
                    f.getName()
            );
            HttpEntity multipart = builder.build();
            uploadFile.setEntity(multipart);
            HttpResponse response = httpClient.execute(uploadFile);
            responseText = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            Log.exceptionCatch("Exception caught:", e);
        }
    }

    public static int parseForInt(String xPathValue) {
        Log.info("Parsing Json request for needed number");
        ReadContext ctx = JsonPath.parse(responseText);
        return ctx.read(xPathValue);
    }

    public static String parseForText(String xPathValue) {
        Log.info("Parsing Json request for needed text");
        ReadContext ctx = JsonPath.parse(responseText);
        return ctx.read(xPathValue);
    }

    public static JSONArray parseForArray(String xPathValue) {
        Log.info("Parsing Json request for needed array");
        ReadContext ctx = JsonPath.parse(responseText);
        return ctx.read(xPathValue);
    }
}

