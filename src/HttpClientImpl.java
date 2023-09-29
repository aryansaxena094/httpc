import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.DatagramSocket;
import java.net.Socket;
import java.nio.Buffer;


public class HttpClientImpl implements HttpClient {
    Socket socket;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String GET(HttpRequest request) {
        String requestString = RequestFormatter.formatRequest(request);
        String responseString = sendRequest(requestString);
        HttpResponse response = ResponseParser.parseResponse(responseString);
        return response.getBody();
    }

    @Override
    public String POST(HttpRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'POST'");
    }

    public String sendRequest(String request){
        try{
            socket = new Socket("localhost", 8080);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(socket.getInputStream()));

            String requestString = RequestFormatter.formatRequest(request);
        }
        throw new UnsupportedOperationException("Unimplemented method 'sendRequest'");
    }




    
}
