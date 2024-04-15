package controller;

import webserver.request.Request;
import webserver.response.ApiResponse;
import webserver.response.FileResponse;
import webserver.response.Response;

public class CommonController {
    public static Response handleHome(Request request) {
        return ApiResponse.of("Hello World");
    }

    public static Response handleFile(Request request) {
        return FileResponse.of(request.getPath().getRoutePath());
    }

    private CommonController() {
    }
}
