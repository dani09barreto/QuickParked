package org.devscite.Utils.Exeptions;

/**
 * Vehicle could not be found inside the database or Controller,
 * may happen with an unregistered license plate or lower case license plate
 */
public class VehicleNotExist extends Exception{
    public VehicleNotExist(String msg){
        super (msg);
    }
}
