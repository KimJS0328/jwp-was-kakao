package webserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import utils.IOUtils;
import webserver.Cookie;

public class Request {

    private final RequestHeader requestHeader;
    private final RequestBody requestBody;

    public Request(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        requestHeader = new RequestHeader(IOUtils.readLinesUntilBlankLine(reader));
        requestBody = new RequestBody(IOUtils.readData(reader, requestHeader.getContentLength()));
    }

    public Path getPath() {
        return requestHeader.getRequestLine().getPath();
    }

    public Method getMethod() {
        return requestHeader.getRequestLine().getMethod();
    }

    public Map<String, String> getBody() {
        return requestBody.getKeyValues();
    }

    public Cookie getCookie(String name) {
        return requestHeader.getCookie(name);
    }
}