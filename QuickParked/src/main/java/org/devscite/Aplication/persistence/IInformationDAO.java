package org.devscite.Aplication.persistence;

import org.devscite.Entities.Enums.SlotType;

import java.sql.SQLException;
import java.util.Map;

public interface IInformationDAO {
    void setSlot(SlotType type, Integer size) throws SQLException;

    String getParkingName() throws SQLException;

    void setParkingName(String name) throws SQLException;

    String getParkingAddress() throws SQLException;

    void setParkingAddress(String name) throws SQLException;

    Integer getParkingCarFee() throws SQLException;

    void setParkingCarFee(Integer value) throws SQLException;

    Integer getParkingMotorcycleFee() throws SQLException;

    void setParkingMotorcycleFee(Integer value) throws SQLException;

    Map<SlotType, Integer> getDefinedSlots() throws SQLException;
}
