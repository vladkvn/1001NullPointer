package com.springapp.mvc;

import org.springframework.stereotype.Component;

/**
 * Created by vladkvn on 30.11.2016.
 */
@Component
public class UserDTO {
    protected String login;

    protected String pass;

    public UserDTO(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }

    public UserDTO() {
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

    public void setPass(String pass) {
        this.pass = pass;
    }

}
