import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Starting point
        // while(true){
            try {
                System.out.print("Enter command: ");
                String input = sc.nextLine();
                List<String> data = Arrays.asList(input.split(" "));
                RequestFormatter formatter = new RequestFormatter();
                HttpRequest request = formatter.ParseInput(data);
                HttpClient httpClient = new HttpClientImpl();
                HttpResponse response = httpClient.sendRequest(request);
                response.printResponse();
                sc.close();
            } catch (Exception e) {
            }
    }
}
