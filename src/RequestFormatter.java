public class RequestFormatter {
    public static String formatRequest(HttpRequest request) {
        StringBuilder sb = new StringBuilder();

        // Request Line
        sb.append(request.getMethod())
          .append(" ")
          .append(request.getPath());

        // Query Parameters
        if(request.getQuery() != null && !request.getQuery().isEmpty()) {
            sb.append("?");
            request.getQuery().forEach((key, value) -> sb.append(key).append("=").append(value).append("&"));
            sb.deleteCharAt(sb.length() - 1);  // Remove trailing "&"
        }

        sb.append(" HTTP/1.1\r\n");

        // Headers
        if (request.getHeaders() != null && !request.getHeaders().isEmpty()) {
            request.getHeaders().forEach((key, value) -> sb.append(key).append(": ").append(value).append("\r\n"));
        }

        // Blank Line
        sb.append("\r\n");

        // Body
        if ("POST".equalsIgnoreCase(request.getMethod()) && request.getBody() != null) {
            sb.append(request.getBody());
        }
        System.out.println(sb.toString());
        return sb.toString();
    }
}
