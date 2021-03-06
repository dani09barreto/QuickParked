package org.devscite.Entities.Model;

/**
 * Parking user, may be an Employee or an Administrator
 */
public abstract class UserParking {
    protected String username;
    protected String passWord;

    public UserParking(String username, String passWord) {
        this.username = username;
        this.passWord = passWord;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "UserParking{" +
                       "username='" + username + '\'' +
                       ", passWord='" + passWord + '\'' +
                       '}';
    }
}
