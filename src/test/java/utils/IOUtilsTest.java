package utils;

import static org.assertj.core.api.Assertions.*;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtilsTest {
    private static final Logger logger = LoggerFactory.getLogger(IOUtilsTest.class);

    @Test
    public void readData() throws Exception {
        String data = "abcd123";
        StringReader sr = new StringReader(data);
        BufferedReader br = new BufferedReader(sr);

        logger.debug("parse body : {}", IOUtils.readData(br, data.length()));
    }

    @Test
    public void readLinesUntilBlankLine() {
        String data = "GET /index.html HTTP/1.1\r\n" +
            "Host: www.example.com\r\n" +
            "Content-Type: text/plain\r\n" +
            "Content-Length: 30\r\n" +
            "\r\n" +
            "name=abc&age=20&job=programmer";
        StringReader sr = new StringReader(data);
        BufferedReader br = new BufferedReader(sr);

        assertThat(IOUtils.readLinesUntilBlankLine(br)).containsExactly(
            "GET /index.html HTTP/1.1",
            "Host: www.example.com",
            "Content-Type: text/plain",
            "Content-Length: 30"
        );
    }
}
