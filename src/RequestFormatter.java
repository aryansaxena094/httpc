import java.util.List;

class RequestFormatter {
    private HttpRequest request;

    public RequestFormatter() {
        request = new HttpRequest();
    }

    public HttpRequest ParseInput(List<String> data) {
        request.setMethod(data.get(0).toUpperCase());
        for (int i = 1; i < data.size(); i++) {
            String argument = data.get(i);
            if ("-v".equals(argument)) {
                request.setVerbose(true);
            } else if ("-h".equals(argument)) {
                String headerData = data.get(i + 1);
                String[] header = headerData.split(":");
                request.addHeader(header[0], header[1]);
                i++;
            } else if (argument.startsWith("http://") || argument.startsWith("https://")) {
                    request.setURL(argument);
                    request.extractQueryParams();
            } else if ("-d".equals(argument) || "--d".equals(argument)) {
                String inlineData = data.get(i + 1);
                request.setInlineData(inlineData);
                i++;
            } else if ("-f".equals(argument)) {
                String filePath = data.get(i + 1);
                request.setFilePath(filePath);
                i++;
            } else if ("-o".equals(argument)) {
                String outputFile = data.get(i + 1);
                request.setOutputFile(outputFile);
                i++;
            }
        }
        System.out.println("Request Formatted (RequestFormatter.java))");
        return request;
    }

    


}