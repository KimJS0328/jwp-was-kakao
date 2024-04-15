package webserver;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import webserver.request.RequestHeader;

public class RequestHeaderTest {

    @Test
    public void testRequestHeaderParsing() {
        RequestHeader requestHeader = new RequestHeader(List.of(
            "GET /index.html HTTP/1.1",
            "Host: www.example.com",
            "Content-Type: text/plain",
            "Content-Length: 100"
        ));

        assertThat(requestHeader.getHeader("Host")).isEqualTo("www.example.com");
        assertThat(requestHeader.getHeader("Content-Type")).isEqualTo("text/plain");
        assertThat(requestHeader.getHeader("Content-Length")).isEqualTo("100");
    }
}
