package webserver.request;

public class RequestRoute {
    private final Method method;
    private final Path path;

    private RequestRoute(Method method, Path path) {
        this.method = method;
        this.path = path;
    }

    public static RequestRoute of(Method method, Path path) {
        return new RequestRoute(method, path);
    }

    public static RequestRoute of(Method method, String path) {
        return RequestRoute.of(method, new Path(path));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        RequestRoute that = (RequestRoute)o;

        if (method != that.method)
            return false;
        return path.getRoutePath().equals(that.path.getRoutePath());
    }

    @Override
    public int hashCode() {
        int result = method.hashCode();
        result = 31 * result + path.getRoutePath().hashCode();
        return result;
    }
}
