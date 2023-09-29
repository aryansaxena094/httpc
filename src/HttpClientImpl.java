import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpClientImpl implements HttpClient {
    Socket socket;

        public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public HttpResponse GET(HttpRequest request) {
        return sendRequest(request);
    }

    @Override
    public HttpResponse POST(HttpRequest request) {
        return sendRequest(request);
    }
    
    public HttpResponse sendRequest(HttpRequest request) {
        try (Socket socket = new Socket("localhost", 8080);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String requestString = RequestFormatter.formatRequest(request);
            out.println(requestString);
            out.flush();

            StringBuilder responseStringBuilder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                responseStringBuilder.append(line).append("\n");
            }

            return ResponseParser.parseResponse(responseStringBuilder.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
