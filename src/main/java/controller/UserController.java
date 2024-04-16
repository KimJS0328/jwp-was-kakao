package controller;

import java.io.IOException;

import model.User;
import service.UserService;
import webserver.Cookie;
import webserver.Session;
import webserver.request.Request;
import webserver.response.ApiResponse;
import webserver.response.NotFoundResponse;
import webserver.response.Response;
import webserver.response.TemplateResponse;

public class UserController {

    public static Response handleUserCreate(Request request) {
        User user = UserService.addUser(new User(request.getBody()));
        String location = "/index.html";
        return ApiResponse.of(user.toString(), location);
    }

    public static Response handleUserLogin(Request request) {
        Session session = UserService.authenticate(request.getBody().get("userId"), request.getBody().get("password"));
        if (session == null) {
            return ApiResponse.of("Authentication failed", "/user/login_failed.html");
        }
        User user = (User)session.getAttribute("user");
        ApiResponse response = ApiResponse.of(user.toString(), "/index.html");
        response.setCookie(new Cookie("JSESSIONID", session.getId()).addAttribute("PATH", "/"));
        return response;
    }

    public static Response handleUserList(Request request) {
        Session session = UserService.authorize(request.getCookie("JSESSIONID"));
        if (session == null) {
            return ApiResponse.of("Authorization failed", "/user/login.html");
        }
        try {
            return TemplateResponse.of("user/list", UserService.getUserList());
        } catch (IOException e) {
            return new NotFoundResponse();
        }
    }

    private UserController() {
    }
}
