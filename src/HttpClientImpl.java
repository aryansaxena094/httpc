import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
public class HttpClientImpl implements HttpClient {
    @Override
    public HttpResponse sendRequest(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        try {
            Socket socket = new Socket(request.getHost(), 80);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            if(request.isFile()){
                String filePath = request.getFilePath();
                String fileContent = new String(Files.readAllBytes(Paths.get("src/"+filePath)));
                request.setBody(fileContent);
            }
            System.out.println(request.toHttpRequestString());
            out.print(request.toHttpRequestString());
            out.flush();
            ResponseParser parser = new ResponseParser();
            response = parser.parseResponse(in);
            if (!request.isVerbose()) {
                response.setStatusCode(0);  
                response.clearHeaders();  
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error sending request");
            e.printStackTrace();
        }
        return response;
    }
}
