package service;

import db.DataBase;
import model.User;

public class UserService {
    public static User addUser(User user) {
        DataBase.addUser(user);
        return user;
    }

    public static User getUser(String userId) {
        return DataBase.findUserById(userId);
    }
}
