package org.devscite.Aplication.persistence;

import org.devscite.Entities.Model.Employee;
import org.devscite.Entities.Model.UserParking;
import org.devscite.Utils.Exeptions.InvalidUser;

import java.util.ArrayList;

public interface IUserDAO {
    Integer addUser(Employee employee);
    void addEmployee(Employee employee);
    void updateUser(UserParking userParking);
    void deleteUser(UserParking userParking);
    ArrayList<Employee> listWorkers ();
    UserParking searchUser(String username, String passWord) throws InvalidUser;
}
