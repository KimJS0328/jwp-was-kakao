package webserver;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import webserver.request.Path;

public class PathTest {
    @Test
    public void 여러_파라미터를_파싱할_수_있다() {
        String pathString = "/index.html?name=abc&age=20&job=programmer";
        Path path = new Path(pathString);
        assertThat(path.getRoutePath()).isEqualTo("/index.html");

        Map<String, String> params = path.getParams();

        assertThat(params).containsExactlyInAnyOrderEntriesOf(Map.of(
            "name", "abc",
            "age", "20",
            "job", "programmer"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/index.html?name=abc", "/index.html?name=abc&"})
    public void 단일_파라미터를_파싱할_수_있다(String pathString) {
        Path path = new Path(pathString);
        assertThat(path.getRoutePath()).isEqualTo("/index.html");

        Map<String, String> params = path.getParams();

        assertThat(params).containsExactly(Map.entry("name", "abc"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/index.html", "/index.html?"})
    public void 파라미터가_없는_경우_파싱할_수_있다(String pathString) {
        Path path = new Path(pathString);
        assertThat(path.getRoutePath()).isEqualTo("/index.html");

        Map<String, String> params = path.getParams();

        assertThat(params).isEmpty();
    }
}