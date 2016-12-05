package com.springapp.mvc;

import java.sql.SQLException;

/**
 * Created by vladkvn on 12.11.2016.
 */
public interface Connect {
    public boolean chLogin(UserDTO userdto);
    public boolean chLoginAndPass(UserDTO userdto);
    public boolean addUser(UserDTO userdto);
    public User getUser(UserDTO userdto);
    public void updateInfo(String login, Info info);
    public User getUserByLogin(String login);


    public Info getInfoByLogin(String login);


}
