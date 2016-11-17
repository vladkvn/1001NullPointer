package com.springapp.mvc;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;

/**
 * Created by vladkvn on 12.11.2016.
 */
@SuppressWarnings("JpaQueryApiInspection")
@Component
public class SQLConnect implements Connect
{
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Statement statement;
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
    @Override
    public void test() throws SQLException {
        statement=connection.createStatement();
        preparedStatement = connection.prepareStatement("INSERT INTO `trainingdb`.`users` (`id`, `login`, `pass`) VALUES (null, ?, ?);");
        preparedStatement.setString(1,"qqwef");
        preparedStatement.setString(2,"qqwef");
        preparedStatement.executeUpdate();
    }

    @Override
    public boolean chLogin(User user) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM trainingdb.users\n" +
                "where login=?;");
        preparedStatement.setString(1, user.getLogin());
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return true;
        else
            return false;
    }

    @Override
    public boolean chLoginAndPass(User user) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT * FROM `trainingdb`.`users`\n" +
                "where (login=? and pass=?);");
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2,user.getPass());
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next())
            return true;
        else
            return false;
    }


    @Override
    public Info getInfo(User user) throws SQLException {
        Info info = new Info();
        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT `name`, `City` FROM trainingdb.info\n" +
                "WHERE id=?;");
        preparedStatement2.setInt(1, 1);
        resultSet = preparedStatement2.executeQuery();
        if(resultSet.next()) {
            info.setName(resultSet.getString("name"));
            info.setCity(resultSet.getString("City"));
        }
        return info;
    }

    public int getId(String login) throws SQLException {
        preparedStatement = connection.prepareStatement("SELECT id FROM `trainingdb`.`users`\n" +
                "where login=?;");
        preparedStatement.setString(1, login);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            return resultSet.getInt("id");
        }
        return 0;
    }

    @Override
    public boolean add(User user) throws SQLException {
        if(!this.chLogin(user)) {
            preparedStatement = connection.prepareStatement("INSERT INTO `trainingdb`.`users` (`id`, `login`, `pass`) VALUES (null, ?, ?);");
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPass());
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement2;
            preparedStatement2 = connection.prepareStatement("INSERT INTO `trainingdb`.`info` (`id`, `name`, `City`) VALUES (?, 'empty', 'empty');");
            preparedStatement2.setInt(1, this.getId(user.getLogin()));
            preparedStatement2.executeUpdate();
            return true;
        }
        else return false;
    }

    @Override
    public boolean updateInfo(String login, Info info) throws SQLException {
        if(this.chLogin(new User(login))) {
            preparedStatement = connection.prepareStatement("UPDATE `trainingdb`.`info`" +
                    "set name = ?, City=?");
            preparedStatement.setString(1, info.getName());
            preparedStatement.setString(2, info.getCity());
            preparedStatement.executeUpdate();
            return true;
        }
        else return false;
    }
}
