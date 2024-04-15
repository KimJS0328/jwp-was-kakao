package service;

import db.DataBase;
import model.User;

public class UserService {

    public static User addUser(User user) {
        DataBase.addUser(user);
        return user;
    }
}
