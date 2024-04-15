package webserver;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import webserver.request.Method;
import webserver.request.RequestLine;

public class RequestLineTest {

    @Test
    public void testRequestLineParsing() {
        String requestLineString = "GET /index.html HTTP/1.1";

        RequestLine requestLine = new RequestLine(requestLineString);

        assertThat(requestLine.getMethod()).isEqualTo(Method.GET);
        assertThat(requestLine.getPath().getRoutePath()).isEqualTo("/index.html");
        assertThat(requestLine.getProtocol().getProtocol()).isEqualTo("HTTP/1.1");
    }
}