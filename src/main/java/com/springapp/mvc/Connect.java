package com.springapp.mvc;

import java.sql.SQLException;

/**
 * Created by vladkvn on 12.11.2016.
 */
public interface Connect {
    public boolean chLogin(User user) throws SQLException;
    public boolean chLoginAndPass(User user) throws SQLException;
    public void test() throws SQLException;
    public boolean add(User user) throws SQLException;
    public int getId(String login) throws SQLException;
    public Info getInfo(User user) throws SQLException;
    public boolean updateInfo(String login, Info info) throws SQLException;
}
