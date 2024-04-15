package webserver;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import webserver.request.RequestBody;

public class RequestBodyTest {

    @Test
    public void testRequestBodyParsing() {
        String bodyContent = "name=abc&age=20&job=programmer";

        RequestBody requestBody = new RequestBody(bodyContent);

        assertThat(requestBody.get("name")).isEqualTo("abc");
        assertThat(requestBody.get("age")).isEqualTo("20");
        assertThat(requestBody.get("job")).isEqualTo("programmer");
    }
}