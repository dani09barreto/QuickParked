package org.devscite.Utils.Exeptions;

/**
 * License plate checker failed, invalid regular expression
 */
public class InvalidLicensePlate extends Exception {
    public InvalidLicensePlate(String cause) {
        super(cause);
    }
}
