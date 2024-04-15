package webserver.request;

public class RequestLine {

    public static final int METHOD_INDEX = 0;
    public static final int PATH_INDEX = 1;
    public static final int PROTOCOL_INDEX = 2;
    private Method method;
    private Path path;
    private Protocol protocol;

    public RequestLine(String requestLine) {
        String[] tokens = requestLine.split(" ");
        this.method = Method.of(tokens[METHOD_INDEX]);
        this.path = new Path(tokens[PATH_INDEX]);
        this.protocol = new Protocol(tokens[PROTOCOL_INDEX]);
    }

    public Method getMethod() {
        return method;
    }

    public Path getPath() {
        return path;
    }

    public Protocol getProtocol() {
        return protocol;
    }
}
