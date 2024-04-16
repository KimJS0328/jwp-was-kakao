package controller;

import java.util.UUID;

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

    public static Response handleUserLogin(Request request) {
        User user = authenticate(request);
        if (user == null) {
            return ApiResponse.of("Authentication failed", "/user/login_failed.html");
        }
        ApiResponse response = ApiResponse.of(user.toString(), "/index.html");
        response.setCookie("JSESSIONID", String.valueOf(UUID.randomUUID()));
        response.setCookie("PATH", "/");
        return response;
    }

    private static User authenticate(Request request) {
        String userId = request.getBody().get("userId");
        String password = request.getBody().get("password");
        User user = UserService.getUser(userId);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    private UserController() {
    }
}
