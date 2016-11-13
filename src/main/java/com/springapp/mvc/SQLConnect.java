package com.springapp.mvc;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;

/**
 * Created by vladkvn on 12.11.2016.
 */
@Component
public class SQLConnect implements Connect
{
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    @PostConstruct
    public void postInit()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainingdb", "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @PreDestroy
    public void preDestroy() throws SQLException {
        connection.close();
    }

    public void test() throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * from users");
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("login"));
        }
    }
    @Override
    public boolean add(User user) {
        return false;
    }
}
