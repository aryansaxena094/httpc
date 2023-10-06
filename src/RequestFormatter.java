import java.io.File;
import java.util.List;

class RequestFormatter {
    private HttpRequest request;

    public RequestFormatter() {
        request = new HttpRequest();
    }

    public HttpRequest ParseInput(List<String> data) {
        request.setMethod(data.get(0).toUpperCase());

        // Check for mutually exclusive flags and method constraints
        boolean hasDataFlag = false;
        boolean hasFileFlag = false;
        boolean hasURL = false;

        for (int i = 1; i < data.size(); i++) {
            String argument = data.get(i);

            if (("-h".equals(argument) || "-d".equals(argument) || "-f".equals(argument) || "-o".equals(argument))
                    && (i + 1 >= data.size())) {
                throw new IllegalArgumentException("Missing argument for flag: " + argument);
            }

            if ("-v".equals(argument)) {
                request.setVerbose(true);

            } else if ("-h".equals(argument)) {
                String headerData = data.get(i + 1);
                String[] header = headerData.split(":");
                if (header.length != 2) {
                    throw new IllegalArgumentException("Header must be in the format 'Key:Value'.");
                }
                request.addHeader(header[0], header[1]);
                i++;

            } else if (argument.contains("http://") || argument.contains("https://")) {
                hasURL = true;
                if ((argument.startsWith("\"") && argument.endsWith("\""))
                        || (argument.startsWith("'") && argument.endsWith("'"))) {
                    argument = argument.substring(1, argument.length() - 1);
                }
                request.setURL(argument);
                request.extractQueryParams();

            } else if ("-d".equals(argument)) {
                hasDataFlag = true;
                String inlineData = data.get(i + 1);
                if (!(inlineData.startsWith("\"") && inlineData.endsWith("\""))
                        && !(inlineData.startsWith("'") && inlineData.endsWith("'"))) {
                    throw new IllegalArgumentException("Inline data must be inside quotes.");
                }
                inlineData = inlineData.substring(1, inlineData.length() - 1); // Removing quotes
                request.setInlineData(inlineData);
                i++;
            } else if ("-f".equals(argument)) {
                hasFileFlag = true;
                String filePath = data.get(i + 1);
                File file = new File(filePath);
                if (!file.exists() || !file.isFile()) {
                    throw new IllegalArgumentException("Invalid file path or file does not exist: " + filePath);
                }
                request.setFilePath(filePath);
                i++;
            } else if ("-o".equals(argument)) {
                String outputFile = data.get(i + 1);
                request.setOutputFile(outputFile);
                i++;
                request.setOutputToFile(true);
            }
        }

        if (!hasURL) {
            throw new IllegalArgumentException("No URL provided.");
        }
        
        if (hasDataFlag && hasFileFlag) {
            throw new IllegalArgumentException("Cannot use both -d and -f flags together.");
        }
        
        String method = request.getMethod();

        if (("GET".equals(method) || "HELP".equals(method)) && (hasDataFlag || hasFileFlag)) {
            throw new IllegalArgumentException("-d or -f cannot be used with GET or HELP requests.");
        }
        return request;
    }
}