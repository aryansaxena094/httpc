import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class httpc {
    public static void main(String[] args) {
        try {
            List<String> data = Arrays.asList(args);
            String command = data.get(0).toUpperCase();
            switch (command) {
                case "GET":
                case "POST":
                    RequestFormatter formatter = new RequestFormatter();
                    HttpRequest request = formatter.ParseInput(data);
                    HttpResponse response = sendRequest(request);
                    response.printResponse();
                    break;
                case "HELP":
                    HelpOptions helpOptions = new HelpOptions(data.size() > 1 ? data.get(1) : "general");
                    helpOptions.printHelp();
                    break;
                default:
                    System.out.println("Invalid command, please try again.");
            }
        } catch (Exception e) {
            System.out.println("An error ocurred " + e.getMessage());
        }

    }

    public static HttpResponse sendRequest(HttpRequest request) {
        HttpResponse response = new HttpResponse();
        try (
            Socket socket = new Socket(request.getHost(), 80);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));)
            {
            if (request.isFile()) {
                String filePath = request.getFilePath();
                String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                request.setBody(fileContent);
            }
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
