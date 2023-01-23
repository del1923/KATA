package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    /*String sql="";

    private void ExecuteOper(String sql) {
        Connection connection = Util.cteateConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
*/
    public void createUsersTable() {
/*
        sql= """
               CREATE TABLE User (
               id serial primary key,
               name varchar(40),
               lastName varchar(40),
               age int
               )
                """;
        ExecuteOper(sql);

        Connection connection = Util.cteateConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/



        /* написать создание таблицы User:
        1) устанавливаем соединение с БД
        2) формируется SQL запрос создания таблицы
        3) обработать возможные исключения
        */


    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {



    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
