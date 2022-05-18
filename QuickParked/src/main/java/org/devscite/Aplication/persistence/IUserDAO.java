package org.devscite.Aplication.persistence;

import org.devscite.Entities.Model.User;

import java.util.ArrayList;

public interface IUserDAO {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
    ArrayList<User> listUsers ();
}
