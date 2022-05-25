package org.devscite.Aplication.aggregates;

import org.devscite.Aplication.persistence.IUserDAO;
import org.devscite.Entities.Model.Admin;
import org.devscite.Entities.Model.Constants;
import org.devscite.Entities.Model.Employee;
import org.devscite.Entities.Model.UserParking;
import org.devscite.Utils.Exeptions.InvalidUser;

import java.sql.*;
import java.util.ArrayList;

public class UserDAOImpl implements IUserDAO{

    @Override
    public void addUser(UserParking userParking) {

    }

    @Override
    public void updateUser(UserParking userParking) {

    }

    @Override
    public void deleteUser(UserParking userParking) {

    }

    @Override
    public ArrayList<UserParking> listUsers() {
        return null;
    }

    @Override
    public UserParking searchUser(String username, String passWord) throws InvalidUser {
        int registros = 0;
        UserParking userParking = null;
        String SQL = "select USERPARKING.USERNAME, EMPLOYEE.USERID, USERPARKING.PASSWORD, EMPLOYEE.NAME, EMPLOYEE.DOCUMENT, EMPLOYEE.CELLPHONE\n" +
                             "from USERPARKING, EMPLOYEE\n" +
                             "where USERPARKING.ID = EMPLOYEE.USERID and USERPARKING.USERNAME = ? and USERPARKING.PASSWORD = ?";
        try (
                Connection conex = DriverManager.getConnection(Constants.THINCONN, Constants.USERNAME, Constants.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL)) {
            //se asignan los valores a los parametros
            ps.setString(1, username);
            ps.setString(2, passWord);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    userParking = bulidUsers(rs);
                    registros ++;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex);
            ex.printStackTrace();
        }
        if (registros == 0){
            throw new InvalidUser("no se puede iniciar sesion");
        }
        return userParking;
    }
    public UserParking bulidUsers (final ResultSet rs) throws SQLException {
        UserParking us;
        if (rs.getInt("USERID") == 4){
            us = new Admin(
                    rs.getString("USERNAME"),
                    rs.getString("PASSWORD")
            );
        }else {
            us = new Employee(
                    rs.getString("USERNAME"),
                    rs.getString("PASSWORD"),
                    rs.getString("NAME"),
                    rs.getBigDecimal("DOCUMENT"),
                    rs.getBigDecimal("CELLPHONE")
            );
        }
        return us;
    }
}
