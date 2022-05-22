package org.devscite.Aplication.persistence;

import org.devscite.Entities.Model.UserParking;
import org.devscite.Utils.Exeptions.InvalidUser;

import java.util.ArrayList;

public interface IUserDAO {
    void addUser(UserParking userParking);
    void updateUser(UserParking userParking);
    void deleteUser(UserParking userParking);
    ArrayList<UserParking> listUsers ();
    UserParking searchUser(String username, String passWord) throws InvalidUser;
}
