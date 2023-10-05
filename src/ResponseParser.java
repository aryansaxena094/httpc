import java.io.BufferedReader;
import java.io.IOException;

class ResponseParser {
    public HttpResponse parseResponse(BufferedReader reader) throws IOException {
        HttpResponse response = new HttpResponse();

        String statusLine = reader.readLine();
        String[] statusParts = statusLine.split(" ");
        response.setHttpVersion(statusParts[0]);
        response.setStatusCode(Integer.parseInt(statusParts[1]));
        response.setReasonPhrase(statusParts[2]);

        String headerLine;
        while (!(headerLine = reader.readLine()).isEmpty()) {
            String headerParts[] = headerLine.split(": ");
            response.addHeader(headerParts[0], headerParts[1]);
        }

        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line).append("\n");
        }
        response.setBody(body.toString());

        return response;
    }

    
}