import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
public class HttpClient {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean q = false;
        while (!q) {
            System.out.print("Enter command: ");
            String input = sc.nextLine();
            List<String> data = Arrays.asList(input.split(" "));
            String command = data.get(0);
                switch (command) {
                    case "GET":
                    case "POST":
                    case "get":
                    case "post":
                        data = data.subList(1, data.size()); 
                        RequestFormatter formatter = new RequestFormatter();
                        HttpRequest request = formatter.ParseInput(data);
                        HttpResponse response = sendRequest(request);
                        response.printResponse();
                        break;
                    case "help":
                        HelpOptions helpOptions = new HelpOptions(data.size() > 2 ? data.get(2) : "general");
                        helpOptions.printHelp();
                        break;
                    default:
                        System.out.println("Invalid command, please try again.");
                }
            }
        sc.close();
    }

    public static HttpResponse sendRequest(HttpRequest request) {
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
