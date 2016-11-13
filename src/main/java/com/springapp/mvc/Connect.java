package com.springapp.mvc;

import java.sql.SQLException;

/**
 * Created by vladkvn on 12.11.2016.
 */
public interface Connect {
    public void test() throws SQLException;
    public boolean add(User user);
}
