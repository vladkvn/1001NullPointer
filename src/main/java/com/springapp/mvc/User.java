package com.springapp.mvc;

/**
 * Created by vladkvn on 12.11.2016.
 */
public class User {
    private int id;
    private String login;
    private String pass;

    public User(String login)
    {
        this.login = login;
    }

    public User(int id, String login) {
        this.id=id;
        this.login=login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
