package utils;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class ParseUtilsTest {
    @Test
    void parseParamTest() {
        String httpRequest = "name=abc&age=20&job=programmer";
        Map<String, String> paramMap = ParseUtils.parseKeyValues(httpRequest);
        assertThat(paramMap.get("name")).isEqualTo("abc");
        assertThat(paramMap.get("age")).isEqualTo("20");
        assertThat(paramMap.get("job")).isEqualTo("programmer");
    }
}