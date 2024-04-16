package webserver.response;

import webserver.Cookie;

public class HeaderBuilder {
    private final StringBuilder builder;
    private Status status;
    private final Cookie cookie;

    public HeaderBuilder() {
        this.builder = new StringBuilder();
        this.status = Status.OK;
        this.cookie = new Cookie();
    }

    public HeaderBuilder setStatus(int status) {
        this.status = Status.of(status);
        return this;
    }

    public HeaderBuilder setStatus(Status status) {
        this.status = status;
        return this;
    }

    public HeaderBuilder addHeader(String key, String value) {
        builder.append(key)
            .append(": ")
            .append(value)
            .append("\r\n");
        return this;
    }

    public HeaderBuilder setCookie(String key, String value) {
        cookie.setCookie(key, value);
        return this;
    }

    public HeaderBuilder addContentType(String contentType) {
        return addHeader("Content-Type", contentType);
    }

    public HeaderBuilder addContentLength(int length) {
        return addHeader("Content-Length", String.valueOf(length));
    }

    public byte[] build() {
        return ("HTTP/1.1 " + status.getCode() + " " + status.getMessage() + "\r\n"
            + builder
            + cookie
            + "\r\n").getBytes();
    }
}
