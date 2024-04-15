package utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class ParseUtils {
    public static Map<String, String> parseKeyValues(String keyValues) {
        Map<String, String> keyValuesMap = new HashMap<>();
        Arrays.stream(keyValues.split("&"))
            .filter(Predicate.not(String::isBlank))
            .map(keyValue -> keyValue.split("="))
            .forEach(keyValue -> keyValuesMap.put(keyValue[0], keyValue[1]));
        return Collections.unmodifiableMap(keyValuesMap);
    }

    private ParseUtils() {
    }
}