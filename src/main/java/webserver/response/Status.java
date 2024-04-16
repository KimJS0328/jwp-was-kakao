package webserver.response;

import java.util.Arrays;

public enum Status {
    OK(200, "OK"),
    FOUND(302, "Found"),
    NOT_FOUND(404, "Not Found");

    private final int code;
    private final String message;

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Status of(int code) {
        return Arrays.stream(values())
            .filter(status -> status.code == code)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid status code: " + code));
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
