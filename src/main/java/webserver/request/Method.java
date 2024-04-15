package webserver.request;

import java.util.Locale;

public enum Method {
    GET,
    POST;

    public static Method of(String method) {
        return Method.valueOf(method.toUpperCase(Locale.ROOT));
    }
}