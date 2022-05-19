package org.devscite.Entities.Model;

public abstract class User {
    protected String username;
    protected String passWord;

    public User(String username, String passWord) {
        this.username = username;
        this.passWord = passWord;
    }
}
