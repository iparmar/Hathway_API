package gracenote.resources;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by iparmar on 02/10/2018.
 */
public class HttpClient {
    private String URL;
    private org.apache.http.client.HttpClient client;
    private HttpGet getRequest;
    private HttpResponse response;
    private String responseString = "";
    private GetDataFromPropertyFile getDataFromPropertyFile;

    public HttpClient() {
        getDataFromPropertyFile = new GetDataFromPropertyFile();
        client = HttpClientBuilder.create().build();
    }

    public void checkStatusForAppServices() throws IOException {
        URL = getDataFromPropertyFile.getApiServer();
        getRequest = new HttpGet(URL);
        response = client.execute(getRequest);
        responseString = getResponseString();
    }

    private String getResponseString() throws IOException {
        String resp = "";
        String var = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(this.response.getEntity().getContent()));
        while ((var = br.readLine()) != null) {
            resp = resp + var;
        }
        return resp;
    }

    public int getResponseStatusCode() {
        return response.getStatusLine().getStatusCode();
    }

    public void setApiUrl(String api) {
        String apiUrl=null;
        switch (api) {
            case "Login API":
                apiUrl = getDataFromPropertyFile.getLoginInfo();
                break;
        }
        URL = URL + apiUrl;
    }

    public void performGETRequest() throws IOException {
        getRequest = new HttpGet(URL);
        response = client.execute(getRequest);
        responseString = getResponseString();
    }

    public String getJSONResponse() throws IOException {
        return responseString;
    }

    public String getAPIUrl() {
        return URL;
    }

    public void constructParameterString(Map<String, String> params) {
        if (!URL.endsWith("?"))
            URL = URL + "?";

        String parameterString = "", value = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {

            if (entry.getValue().contains("&")) {
                value = entry.getValue().replace("&", "%26");
                parameterString = parameterString + entry.getKey() + "=" + value + "&";
            } else {
                parameterString = parameterString + entry.getKey() + "=" + entry.getValue() + "&";
            }

            if (parameterString.contains(" ")) {
                parameterString = parameterString.replaceAll(" ", "+");
            }
        }

        URL = URL + parameterString;
    }

    public int checkIfFileExists(String programImageFilePath) throws IOException {
        try {
            URL = programImageFilePath;
            performGETRequest();
            return response.getStatusLine().getStatusCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
