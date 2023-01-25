package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/*
    Тут создаём один раз подключение и
    методы с запросами на таблицу user
 */


public class UserDaoJDBCImpl implements UserDao {

    Connection connection = Util.cteateConnection();//создаём подключение


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        /* написать создание таблицы User:
        1) устанавливаем соединение с БД -> вынести перед методами
        2) формируется SQL запрос создания таблицы
        3) обработать возможные исключения
        */
        final String SQL = "CREATE TABLE IF NOT EXISTS User " +
                        "(id int not null primary key auto_increment," +
                        "name varchar(40) not null," +
                        "lastname varchar(40)," +
                        "age int)";

        try {
            Statement statement = connection.createStatement(); // создаём statement
            statement.executeUpdate(SQL); //выполняем запрос SQL
        } catch (SQLException e) { //если отловили исключение
            throw new RuntimeException(e); //роняем программу
        }
    }

    public void dropUsersTable() {
        final String SQL = "DROP TABLE IF EXISTS User"; //удаление таблицы
        try {
            Statement statement = connection.createStatement(); // создаём statement
            statement.executeUpdate(SQL); //выполняем запрос SQL
        } catch (SQLException e) { //если отловили исключение
            throw new RuntimeException(e); //роняем программу
        }


    }

    public void saveUser( String name, String lastname, byte age) {
        final String SQL = "INSERT INTO User (name, lastname, age) VALUES ( ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(SQL)) { // создаём statement
                statement.setString(1, name);
                statement.setString(2, lastname);
                statement.setByte(3, age);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {
        final String SQL = "DELETE FROM User"; //очищаем таблицу
        try {
            Statement statement = connection.createStatement(); // создаём statement
            statement.executeUpdate(SQL); //выполняем запрос SQL
        } catch (SQLException e) { //если отловили исключение
            throw new RuntimeException(e); //роняем программу
        }
    }
}
