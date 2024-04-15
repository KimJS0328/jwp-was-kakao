package webserver;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import webserver.request.Request;

public class RequestTest {
    @Test
    void Body가_없는_HTTP_요청을_처리할_수_있다() throws IOException {
        // given
        String request_stream = "GET /index.html HTTP/1.1\r\n"
            + "Host: localhost:8080\r\n"
            + "Connection: keep-alive\r\n"
            + "Accept: */*\r\n"
            + "\r\n";

        // when
        Request request = new Request(new ByteArrayInputStream(request_stream.getBytes()));

        // then
        assertThat(request.getPath().getRoutePath()).isEqualTo("/index.html");
    }

    @Test
    void Body가_있는_HTTP_요청을_처리할_수_있다() throws IOException {
        // given
        String request_stream = "POST /index.html HTTP/1.1\r\n"
            + "Host: localhost:8080\r\n"
            + "Connection: keep-alive\r\n"
            + "Accept: */*\r\n"
            + "Content-Length: 30\r\n"
            + "\r\n"
            + "name=abc&age=20&job=programmer";

        // when
        Request request = new Request(new ByteArrayInputStream(request_stream.getBytes()));

        // then
        assertThat(request.getPath().getRoutePath()).isEqualTo("/index.html");
        assertThat(request.getBody().get("name")).isEqualTo("abc");
        assertThat(request.getBody().get("age")).isEqualTo("20");
        assertThat(request.getBody().get("job")).isEqualTo("programmer");
    }
}
