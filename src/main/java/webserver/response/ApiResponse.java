package webserver.response;

import webserver.Cookie;

public class ApiResponse implements Response {
    private final String data;
    private final HeaderBuilder builder;

    private ApiResponse(String data, Status status, String redirectUrl) {
        this.data = data;
        this.builder = new HeaderBuilder();
        builder.setStatus(status)
            .addHeader("Content-Type", "application/json;charset=utf-8")
            .addContentLength(data.length());
        if (redirectUrl != null) {
            builder.addHeader("Location", redirectUrl);
        }
    }

    public static ApiResponse of(String data) {
        return new ApiResponse(data, Status.OK,null);
    }

    public static ApiResponse of(String data, String redirectUrl) {
        return new ApiResponse(data, Status.FOUND, redirectUrl);
    }

    public void setCookie(Cookie cookie) {
        builder.setCookie(cookie);
    }

    @Override
    public byte[] getHeader() {
        return builder.build();
    }

    @Override
    public byte[] getBody() {
        return data.getBytes();
    }
}
