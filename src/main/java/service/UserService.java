package service;

import java.util.Collection;

import db.DataBase;
import model.User;
import webserver.Cookie;

public class UserService {
    public static User addUser(User user) {
        DataBase.addUser(user);
        return user;
    }

    public static User getUser(String userId) {
        return DataBase.findUserById(userId);
    }

    public static Collection<User> getUserList() {
        return DataBase.findAll();
    }

    public static User authenticate(String userId, String password) {
        User user = getUser(userId);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    public static User authorize(Cookie sessionId) {
        if (sessionId == null) {
            return null;
        }
        return new User("tempId", "tempPassword", "tempName", "tempEmail");
    }
}
