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
            String command = "";
            if (data.size() > 0) {
                command = data.get(0).toUpperCase();
            } else {
                System.out.println("No Arguments provided");
                System.exit(0);
            }
            switch (command) {
            case "GET":
            case "POST":
                RequestFormatter formatter = new RequestFormatter();
                HttpRequest request = formatter.ParseInput(data);
                HttpResponse response = sendRequest(request);
                // Redirection
                int maxRedirects = 20; // Maximum number of redirects to follow
                int redirectCount = 0; // Current number of redirects
                do {
                    response = sendRequest(request);
                    if (response.isRedirect()) {
                        redirectCount++;
                        if (redirectCount > maxRedirects) {
                            System.out.println("Max redirects reached");
                            break;
                        }
                        String newURL = response.getRedirectLocation();
                        if (!newURL.startsWith("http")) { // if the new URL is relative
                            newURL = "http://" + request.getHost() + newURL;
                        }
                        System.out.println("Redirecting to " + newURL);
                        request.setURL(newURL);
                    } else {
                        if (request.isOutputToFile()) {
                            response.printResponseToFile(request);
                        } else {
                            response.printResponse(request);
                        }
                        break;
                    }
                } while (true);
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
        try {
            Socket socket = new Socket(request.getHost(), 80);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            if (request.isFile()) {
                String filePath = request.getFilePath();
                String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
                request.setBody(fileContent);
            }
            out.print(request.toHttpRequestString());
            out.flush();
            ResponseParser parser = new ResponseParser();
            response = parser.parseResponse(in);
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
