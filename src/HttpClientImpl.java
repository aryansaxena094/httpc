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
    public String GET(HttpRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'GET'");
    }

    @Override
    public String POST(HttpRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'POST'");
    }

    public String sendRequest(String requestString){
        // TODO Auto-generated method stub
        // socket.close();
        throw new UnsupportedOperationException("Unimplemented method 'sendRequest'");
    }

    


    
}
