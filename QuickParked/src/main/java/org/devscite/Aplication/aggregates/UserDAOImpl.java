package org.devscite.Aplication.aggregates;

import org.devscite.Aplication.persistence.IUserDAO;
import org.devscite.Entities.Model.Admin;
import org.devscite.Entities.Model.Constants;
import org.devscite.Entities.Model.Employee;
import org.devscite.Entities.Model.UserParking;
import org.devscite.Utils.Exeptions.InvalidUser;

import java.sql.*;
import java.util.ArrayList;

public class UserDAOImpl implements IUserDAO {

    @Override
    public void addEmployee(Employee employee) {
        Integer id = addUser(employee);
        System.out.println(id);
        String sql = "INSERT INTO EMPLOYEE (USERID,DOCUMENT,NAME,CELLPHONE) VALUES(?,?,?,?)";
        try (Connection conex = DriverManager.getConnection(
                Constants.THINCONN,
                Constants.USERNAME,
                Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(sql);) {

            ps.setInt(1, id);
            ps.setBigDecimal(2, employee.getDocument());
            ps.setString(3, employee.getName());
            ps.setBigDecimal(4, employee.getNumber());
            ps.executeUpdate();
            System.out.println("ejecutada");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer addUser(Employee employee) {
        Integer id = 0;
        String SQL = "INSERT INTO USERPARKING(USERNAME,PASSWORD) VALUES(?,?)";
        try (Connection conex = DriverManager.getConnection(Constants.THINCONN,
                Constants.USERNAME,
                Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL);
        ) {
            ps.setString(1, employee.getUsername());
            ps.setString(2, employee.getPassWord());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        StringBuilder ConsultS = new StringBuilder("SELECT ID from USERPARKING\n" +
                "WHERE USERNAME = ? AND\n" +
                "      PASSWORD = ?");
        try (
                Connection conex = DriverManager.getConnection(
                        Constants.THINCONN,
                        Constants.USERNAME,
                        Constants.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(ConsultS.toString());
        ) {
            //se asignan los valores a los parametros
            ps.setString(1, employee.getUsername());
            ps.setString(2, employee.getPassWord());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    id = rs.getInt("ID");
                }
            }
            return id;

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex.toString());
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateUser(UserParking userParking) {

    }

    @Override
    public void deleteEmployee(Employee employee) {
        String sql = "DELETE FROM EMPLOYEE WHERE DOCUMENT = ?";
        try (Connection conex = DriverManager.getConnection(Constants.THINCONN,
                Constants.USERNAME,
                Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(sql);
        ) {
            ps.setBigDecimal(1, employee.getDocument());
            ps.executeUpdate();
            deleteUser(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(Employee emp) {
        String Sql = "DELETE FROM USERPARKING WHERE PASSWORD = ? AND USERNAME = ?";
        try (Connection conex = DriverManager.getConnection(Constants.THINCONN,
                Constants.USERNAME,
                Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(Sql);
        ) {
            ps.setString(1, emp.getPassWord());
            ps.setString(2, emp.getUsername());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Employee> listWorkers() {
        ArrayList<Employee> workers = new ArrayList<>();
        StringBuilder SQL =
                new StringBuilder("select u.USERNAME, u.PASSWORD, e.NAME, e.DOCUMENT, e.CELLPHONE\n" +
                        "from EMPLOYEE e, USERPARKING u\n" +
                        "where u.ID = e.USERID and USERNAME != 'Admin'  ");
        try (
                Connection conex = DriverManager.getConnection(Constants.THINCONN, Constants.USERNAME, Constants.PASSWORD);
                PreparedStatement ps = conex.prepareStatement(SQL.toString());) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    workers.add(buildEmployee(rs));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return workers;
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
                    registros++;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex);
            ex.printStackTrace();
        }
        if (registros == 0) {
            throw new InvalidUser("no se puede iniciar sesion");
        }
        return userParking;
    }

    public UserParking bulidUsers(final ResultSet rs) throws SQLException {
        UserParking us;
        if (rs.getInt("USERID") == 4) {
            us = new Admin(
                    rs.getString("USERNAME"),
                    rs.getString("PASSWORD")
            );
        } else {
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

    public Employee buildEmployee(ResultSet rs) throws SQLException {
        return new Employee(
                rs.getString("USERNAME"),
                rs.getString("PASSWORD"),
                rs.getString("NAME"),
                rs.getBigDecimal("DOCUMENT"),
                rs.getBigDecimal("CELLPHONE")
        );
    }
}
