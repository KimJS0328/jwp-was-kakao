package webserver;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import webserver.response.HeaderBuilder;

public class HeaderBuilderTest {
    @Test
    void 응답코드를_포함하는_응답을_생성한다() {
        // given
        HeaderBuilder headerBuilder = new HeaderBuilder();

        // when
        byte[] header = headerBuilder.build();

        // then
        assertThat(header).isEqualTo("HTTP/1.1 200 OK\r\n\r\n".getBytes());
    }

    @Test
    void 응답코드를_바꾸면_해당하는_헤더를_생성한댜() {
        // given
        HeaderBuilder headerBuilder = new HeaderBuilder();

        // when
        byte[] header = headerBuilder.setStatus(302).build();

        // then
        assertThat(header).isEqualTo("HTTP/1.1 302 Found\r\n\r\n".getBytes());
    }

    @Test
    void 응답_타입과_길이를_설정하면_해당하는_헤더를_생성한다() {
        // given
        HeaderBuilder headerBuilder = new HeaderBuilder();

        // when
        byte[] header = headerBuilder
            .addContentType("text/html;charset=utf-8")
            .addContentLength(10)
            .build();

        // then
        assertThat(header).isEqualTo("HTTP/1.1 200 OK\r\nContent-Type: text/html;charset=utf-8\r\nContent-Length: 10\r\n\r\n".getBytes());
    }

    @Test
    void 쿠키를_설정하면_해당하는_헤더를_생성한다() {
        // given
        HeaderBuilder headerBuilder = new HeaderBuilder();

        // when
        byte[] header = headerBuilder
            .setCookie(new Cookie("key", "value").addAttribute("Path", "/"))
            .build();

        // then
        assertThat(header).isEqualTo("HTTP/1.1 200 OK\r\nSet-Cookie: key=value; Path=/\r\n\r\n".getBytes());
    }
}
