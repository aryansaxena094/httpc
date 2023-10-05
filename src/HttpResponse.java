import java.util.HashMap;
import java.util.Map;

class HttpResponse {
    public String HttpVersion;
    public int statusCode;
    public String reasonPhrase;
    public Map<String, String> headers = new HashMap<String, String>();;
    public String body;
    public String getHttpVersion() {
        return HttpVersion;
    }
    public void setHttpVersion(String httpVersion) {
        HttpVersion = httpVersion;
    }
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getReasonPhrase() {
        return reasonPhrase;
    }
    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public void clearHeaders(){
        this.headers.clear();
    }
    public void removeStatusCode(){
        this.statusCode = 0;
    }

    public void printResponse() {
        StringBuilder sb = new StringBuilder();
    
        // Only add status code and reason if statusCode is not 0
        if (this.getStatusCode() != 0) {
            sb.append(this.getHttpVersion()).append(" ").append(this.getStatusCode())
                    .append(" ").append(this.getReasonPhrase()).append("\n");
        }
    
        // Only add headers if they are not null or empty
        if (this.getHeaders() != null && !this.getHeaders().isEmpty()) {
            for (Map.Entry<String, String> header : this.getHeaders().entrySet()) {
                sb.append(header.getKey()).append(": ").append(header.getValue()).append("\n");
            }
        }
    
        // Only add body if it's not null
        if (this.getBody() != null && !this.getBody().isEmpty()) {
            sb.append("\n").append(this.getBody());
        }

        System.out.println(sb.toString());
    }
}