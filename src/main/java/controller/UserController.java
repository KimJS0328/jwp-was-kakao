package controller;

import model.User;
import service.UserService;
import webserver.request.Request;
import webserver.response.ApiResponse;
import webserver.response.Response;

public class UserController {

    public static Response handleUserCreate(Request request) {
        User user = UserService.addUser(new User(request.getBody()));
        String location = "/index.html";
        return ApiResponse.of(user.toString(), location);
    }

    private UserController() {
    }
}
