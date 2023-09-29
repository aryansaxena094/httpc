import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Initialize HTTP client
        HttpClient client = new HttpClientImpl();

        // Create and configure GET request
        Map<String, String> getHeaders = new HashMap<>();  // or populate it as required
        HttpRequest getRequest = new HttpRequest("GET", "/someEndpoint", getHeaders);
    
        Map<String, String> getQueryParams = new HashMap<>();
        getQueryParams.put("param1", "value1");
        getQueryParams.put("param2", "value2");
        getRequest.setQuery(getQueryParams);

        // Send GET request and print response
        HttpResponse getResponse = client.GET(getRequest);
        System.out.println("GET Response Body: " + getResponse.getBody());

        // Create and configure POST request
        Map<String, String> postHeaders = new HashMap<>();
        postHeaders.put("Content-Type", "application/json"); // or populate it as required
        HttpRequest postRequest = new HttpRequest("POST", "/someOtherEndpoint", postHeaders);
        postRequest.setBody("{\"key\": \"value\"}");

        // Send POST request and print response
        HttpResponse postResponse = client.POST(postRequest);
        System.out.println("POST Response Body: " + postResponse.getBody());
    }
}
