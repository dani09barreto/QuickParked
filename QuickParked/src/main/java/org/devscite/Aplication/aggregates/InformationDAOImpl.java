package org.devscite.Aplication.aggregates;

import org.devscite.Aplication.persistence.IInformationDAO;
import org.devscite.Entities.Enums.SlotType;
import org.devscite.Entities.Model.Constants;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class InformationDAOImpl implements IInformationDAO {

    @Override
    public void setSlot(SlotType type, Integer size) throws SQLException {
        String SQL = "UPDATE SLOTDEFPARKING SET SLOTDEFPARKING.QUANTITY = ? WHERE SLOTDEFPARKING.TYPE = ?";
        try (Connection conex = DriverManager.getConnection(Constants.THINCONN, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL)) {
            //se asignan los valores a los parametros
            ps.setString(2, type.name());
            ps.setInt(1, size);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex);
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public String getParkingName() throws SQLException {
        String SQL = "SELECT NAME FROM PARKINGINFO";
        String result = "";
        try (Connection conex = DriverManager.getConnection(Constants.THINCONN, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString("NAME");
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex);
            ex.printStackTrace();
            throw ex;
        }
        return result;
    }

    @Override
    public void setParkingName(String name) throws SQLException {
        String SQL = "UPDATE PARKINGINFO SET PARKINGINFO.NAME = ?";
        try (Connection conex = DriverManager.getConnection(Constants.THINCONN, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL)) {
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex);
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public String getParkingAddress() throws SQLException {
        String SQL = "SELECT ADDRESS FROM PARKINGINFO";
        String result = "";
        try (Connection conex = DriverManager.getConnection(Constants.THINCONN, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getString("ADDRESS");
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex);
            ex.printStackTrace();
            throw ex;
        }
        return result;
    }

    @Override
    public void setParkingAddress(String name) throws SQLException {
        String SQL = "UPDATE PARKINGINFO SET PARKINGINFO.ADDRESS = ?";
        try (Connection conex = DriverManager.getConnection(Constants.THINCONN, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL)) {
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex);
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public Integer getParkingCarFee() throws SQLException {
        String SQL = "SELECT CARFEE FROM PARKINGINFO";
        int result = 0;
        try (Connection conex = DriverManager.getConnection(Constants.THINCONN, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("CARFEE");
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex);
            ex.printStackTrace();
            throw ex;
        }
        return result;
    }

    @Override
    public void setParkingCarFee(Integer value) throws SQLException {
        String SQL = "UPDATE PARKINGINFO SET PARKINGINFO.CARFEE = ?";
        try (Connection conex = DriverManager.getConnection(Constants.THINCONN, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL)) {
            ps.setInt(1, value);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex);
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public Integer getParkingMotorcycleFee() throws SQLException {
        String SQL = "SELECT MOTORCYCLEFEE FROM PARKINGINFO";
        int result = 0;
        try (Connection conex = DriverManager.getConnection(Constants.THINCONN, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("MOTORCYCLEFEE");
            }

        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex);
            ex.printStackTrace();
            throw ex;
        }
        return result;
    }

    @Override
    public void setParkingMotorcycleFee(Integer value) throws SQLException {
        String SQL = "UPDATE PARKINGINFO SET PARKINGINFO.MOTORCYCLEFEE = ?";
        try (Connection conex = DriverManager.getConnection(Constants.THINCONN, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL)) {
            ps.setInt(1, value);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex);
            ex.printStackTrace();
            throw ex;
        }
    }

    @Override
    public Map<SlotType, Integer> getDefinedSlots() throws SQLException {
        String SQL = "SELECT * FROM SLOTDEFPARKING";
        Map<SlotType, Integer> resultmap = new HashMap<>();
        try (Connection conex = DriverManager.getConnection(Constants.THINCONN, Constants.USERNAME, Constants.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(SQL)) {
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                SlotType type = SlotType.valueOf(results.getString("TYPE"));
                Integer quantity = results.getInt("QUANTITY");
                resultmap.put(type, quantity);
            }
        } catch (SQLException ex) {
            System.out.println("Error de conexion:" + ex);
            ex.printStackTrace();
            throw ex;
        }

        return resultmap;
    }
}
