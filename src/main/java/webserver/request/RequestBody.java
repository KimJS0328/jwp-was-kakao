package webserver.request;

import java.util.HashMap;
import java.util.Map;

import utils.ParseUtils;

public class RequestBody {

    private final Map<String, String> keyValueBody;

    public RequestBody(String body) {
        if (body.isEmpty()) {
            this.keyValueBody = new HashMap<>();
            return;
        }
        this.keyValueBody = ParseUtils.parseKeyValues(body);
    }

    public String get(String key) {
        return keyValueBody.get(key);
    }

    public Map<String, String> getKeyValues() {
        return keyValueBody;
    }
}