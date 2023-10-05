import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean q = false;
        while (!q) {
            System.out.print("Enter command: ");
            String input = sc.nextLine();
            List<String> data = Arrays.asList(input.split(" "));
            String command = data.get(0);

            if (command.equals("httpc")) {
                String actualCommand = data.size() > 1 ? data.get(1) : "";
                switch (actualCommand) {
                    case "GET":
                    case "POST":
                    case "get":
                    case "post":
                        data = data.subList(1, data.size()); // Skip "httpc" and the actual command
                        RequestFormatter formatter = new RequestFormatter();
                        HttpRequest request = formatter.ParseInput(data);
                        HttpClient httpClient = new HttpClientImpl();
                        HttpResponse response = httpClient.sendRequest(request);
                        response.printResponse();
                        break;
                    case "help":
                        HelpOptions helpOptions = new HelpOptions(data.size() > 2 ? data.get(2) : "general");
                        helpOptions.printHelp();
                        break;
                    default:
                        System.out.println("Invalid command, please try again.");
                }

            } else if (command.startsWith("q")) {
                q = true;
            } else {
                System.out.println("Invalid command, please try again.");
            }
        }
        sc.close();
    }
}
