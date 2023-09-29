import java.util.Map;

public class HttpRequest {
    String method;
    String path;
    Map<String, String> query;
    Map<String, String> headers;
    String body;
    public void setMethod(String method) {
        this.method = method;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public void setQuery(Map<String, String> query) {
        this.query = query;
    }
    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getMethod() {
        return method;
    }
    public String getPath() {
        return path;
    }
    public Map<String, String> getQuery() {
        return query;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public String getBody() {
        return body;
    }
}
