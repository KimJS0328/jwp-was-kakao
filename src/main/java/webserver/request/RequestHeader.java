package webserver.request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestHeader {

    private final RequestLine requestLine;
    private final Map<String, String> headers;

    public RequestHeader(List<String> headerLines) {
        this.requestLine = new RequestLine(headerLines.get(0));
        this.headers = createHeaders(headerLines.subList(1, headerLines.size()));
    }

    private Map<String, String> createHeaders(List<String> headerLines) {
        Map<String, String> headers = new HashMap<>();
        if (headerLines.isEmpty()) {
            return headers;
        }
        for (String line : headerLines) {
            String[] keyValue = splitKeyValue(line);
            headers.put(keyValue[0], keyValue[1]);
        }
        return headers;
    }

    private String[] splitKeyValue(String line) {
        int splitPosition = line.indexOf(": ");
        if (splitPosition < 0) {
            throw new IllegalArgumentException("Invalid header line: " + line);
        }
        return new String[] {
            line.substring(0, splitPosition),
            line.substring(splitPosition + 2)};
    }

    public RequestLine getRequestLine() {
        return requestLine;
    }

    public String getHeader(String s) {
        return headers.get(s);
    }

    public int getContentLength() {
        return Integer.parseInt(headers.getOrDefault("Content-Length", "0"));
    }
}
