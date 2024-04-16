package webserver.request;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import webserver.Cookie;

public class RequestHeader {

    public static final int HEADER_KEY = 0;
    public static final int HEADER_VALUE = 1;
    private final RequestLine requestLine;
    private final Map<String, String> headers;
    private final Map<String, Cookie> cookies;

    public RequestHeader(List<String> headerLines) {
        this.requestLine = new RequestLine(headerLines.get(0));
        this.headers = createHeaders(headerLines.subList(1, headerLines.size()));
        this.cookies = createCookies(headerLines.subList(1, headerLines.size()));
    }


    private Map<String, String> createHeaders(List<String> headerLines) {
        return headerLines.stream()
            .map(this::splitKeyValue)
            .filter(header -> !header[HEADER_KEY].equals("Cookie"))
            .collect(Collectors.toMap(header -> header[HEADER_KEY], header -> header[HEADER_VALUE]));
    }

    private Map<String, Cookie> createCookies(List<String> headerLines) {
        return headerLines.stream()
            .map(this::splitKeyValue)
            .filter(header -> header[HEADER_KEY].equals("Cookie"))
            .map(keyValue -> new Cookie(keyValue[HEADER_VALUE]))
            .collect(Collectors.toMap(Cookie::getName, cookie -> cookie));
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
    public Cookie getCookie(String name) {
        return cookies.get(name);
    }
}
