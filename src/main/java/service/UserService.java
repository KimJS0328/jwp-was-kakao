package service;

import java.util.Collection;
import java.util.UUID;

import db.DataBase;
import model.User;
import webserver.Cookie;
import webserver.Session;
import webserver.SessionManager;

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

    public static Session authenticate(String userId, String password) {
        User user = getUser(userId);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        String sessionId = UUID.randomUUID().toString();
        Session session = new Session(sessionId);
        session.setAttribute("user", user);
        SessionManager.addSession(session);
        return session;
    }

    public static Session authorize(Cookie sessionId) {
        if (authorized(sessionId)) {
            return null;
        }
        return SessionManager.findSession(sessionId.getValue());
    }

    private static boolean authorized(Cookie sessionId) {
        return sessionId == null || SessionManager.findSession(sessionId.getValue()) == null;
    }
}
