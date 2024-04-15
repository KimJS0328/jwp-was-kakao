package webserver;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import webserver.request.Method;
import webserver.request.Request;
import webserver.request.RequestRoute;
import webserver.response.Response;

public class Dispatcher {

    private final Map<RequestRoute, Function<Request, Response>> controllerMap;
    private Function<Request, Response> defaultHandler = null;

    public Dispatcher(Map<RequestRoute, Function<Request, Response>> controllerMap) {
        this.controllerMap = controllerMap;
    }

    public Dispatcher() {
        this.controllerMap = new HashMap<>();
    }

    public Dispatcher register(RequestRoute requestRoute, Function<Request, Response> handler) {
        controllerMap.put(requestRoute, handler);
        return this;
    }

    public Dispatcher register(Method method, String path, Function<Request, Response> handler) {
        return register(RequestRoute.of(method, path), handler);
    }

    public Dispatcher registerDefault(Function<Request, Response> handler) {
        this.defaultHandler = handler;
        return this;
    }

    public Response dispatch(Request request) {
        RequestRoute requestRoute = RequestRoute.of(request.getMethod(), request.getPath());
        Function<Request, Response> handler = controllerMap.getOrDefault(requestRoute, defaultHandler);
        return handler.apply(request);

    }
}
