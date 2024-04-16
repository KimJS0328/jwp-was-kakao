package webserver.response;

import webserver.Cookie;

public class HeaderBuilder {
    private final StringBuilder builder;
    private Status status;

    public HeaderBuilder() {
        this.builder = new StringBuilder();
        this.status = Status.OK;
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
        return setCookie(new Cookie(key, value));
    }

    public HeaderBuilder setCookie(Cookie cookie) {
        return addHeader("Set-Cookie", cookie.toString());
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
            + "\r\n").getBytes();
    }
}
