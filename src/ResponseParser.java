import java.util.HashMap;
import java.util.Map;

public class ResponseParser {

    public static HttpResponse parseResponse(String responseString) {
        String[] parts = responseString.split("\r\n\r\n", 2);
        String headerPart = parts[0];
        String body = parts.length > 1 ? parts[1] : null;

        String[] headers = headerPart.split("\r\n");
        String[] statusLine = headers[0].split(" ", 3);

        int statusCode = Integer.parseInt(statusLine[1]);

        Map<String, String> headerMap = new HashMap<>();
        for (int i = 1; i < headers.length; i++) {
            String[] header = headers[i].split(": ", 2);
            if (header.length > 1) {
                headerMap.put(header[0], header[1]);
            }
        }

        HttpResponse response = new HttpResponse(statusCode, headerMap, body);
        return response;
    }
}
