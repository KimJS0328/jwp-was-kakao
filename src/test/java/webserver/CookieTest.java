package webserver;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CookieTest {
    @ParameterizedTest
    @CsvSource(value = {
        "JSESSIONID=1234; Path=/; Expires=Wed, 09 Jun 2021 10:18:14 GMT; Max-Age=3600!JSESSIONID!1234",
        "JSESSIONID=1234!JSESSIONID!1234",
    }, delimiter = '!')
    void 쿠키_문자열이_주어지면_쿠키를_생성한다(String cookie, String name, String value) {
        // given
        // when
        Cookie actual = new Cookie(cookie);

        // then
        assertThat(actual.getName()).isEqualTo(name);
        assertThat(actual.getValue()).isEqualTo(value);
    }
}
