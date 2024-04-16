package webserver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Cookie {
    private final Map<String, String> attributes;
    private final String name;
    private final String value;

    public Cookie(String name, Object value) {
        this.name = name;
        this.value = value.toString();
        this.attributes = new HashMap<>();
    }

    public Cookie(String cookie) {
        String[] tokens = cookie.split(";");
        String[] nameValue = tokens[0].split("=");
        this.name = nameValue[0];
        this.value = nameValue[1];
        this.attributes = new HashMap<>();
        Arrays.stream(tokens)
            .skip(1)
            .map(part -> part.split("="))
            .forEach(part -> attributes.put(part[0].trim(), part[1].trim()));
    }

    public Cookie addAttribute(String key, String value) {
        attributes.put(key, value);
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name).append("=").append(value);
        attributes.forEach((key, value) -> builder.append("; ").append(key).append("=").append(value));
        return builder.toString();
    }

    public String getValue() {
        return value;
    }
}
