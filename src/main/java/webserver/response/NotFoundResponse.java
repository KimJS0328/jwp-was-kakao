package webserver.response;

public class NotFoundResponse implements Response {
    @Override
    public byte[] getHeader() {
        return new HeaderBuilder()
            .setStatus(Status.NOT_FOUND)
            .addContentType("application/json;charset=utf-8")
            .addContentLength(0)
            .build();
    }

    @Override
    public byte[] getBody() {
        return new byte[0];
    }

}
