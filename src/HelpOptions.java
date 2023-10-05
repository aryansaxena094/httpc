public class HelpOptions {
    String command;

    public HelpOptions(String command) {
        this.command = command;
    }

    public void printHelp() {
        StringBuilder sb = new StringBuilder();
        switch (command) {
            case "general":
                sb.append("Detailed Usage\n")
                  .append("==============\n")
                  .append("General\n")
                  .append("-------\n")
                  .append("httpc help\n")
                  .append("httpc is a curl-like application but supports HTTP protocol only.\n")
                  .append("\nUsage:\n")
                  .append("  httpc command [arguments]\n")
                  .append("\nThe commands are:\n")
                  .append("  get   Executes a HTTP GET request and prints the response.\n")
                  .append("  post  Executes a HTTP POST request and prints the response.\n")
                  .append("  help  Prints this screen.\n")
                  .append("\nUse \"httpc help [command]\" for more information about a command.\n");
                break;

            case "get":
                sb.append("httpc help get\n")
                  .append("===============\n")
                  .append("Usage: httpc get [-v] [-h key:value] URL\n")
                  .append("\nGet executes a HTTP GET request for a given URL.\n")
                  .append("\n  -v           Prints the detail of the response such as protocol, status, and headers.\n")
                  .append("  -h key:value Associates headers to HTTP Request with the format 'key:value'.\n");
                break;

            case "post":
                sb.append("httpc help post\n")
                  .append("================\n")
                  .append("Usage: httpc post [-v] [-h key:value] [-d inline-data] [-f file] URL\n")
                  .append("\nPost executes a HTTP POST request for a given URL with inline data or from file.\n")
                  .append("\n  -v           Prints the detail of the response such as protocol, status, and headers.\n")
                  .append("  -h key:value Associates headers to HTTP Request with the format 'key:value'.\n")
                  .append("  -d string    Associates an inline data to the body HTTP POST request.\n")
                  .append("  -f file      Associates the content of a file to the body HTTP POST request.\n")
                  .append("\nEither [-d] or [-f] can be used but not both.\n");
                break;

            default:
                sb.append("Invalid command for help, please try again.\n");
        }
        System.out.println(sb.toString());
    }
}
