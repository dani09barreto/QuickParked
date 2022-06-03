package org.devscite.Utils.Exeptions;

/**
 * User was not found or has invalid information schema
 */
public class InvalidUser extends Exception {
    public InvalidUser(String msg) {
        super(msg);
    }
}
