package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/*
    Тут создаём методы и запросы на таблицу
 */


public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    Connection connection = Util.cteateConnection(); //создаём подключение


    public void createUsersTable() {
        /* написать создание таблицы User:
        1) устанавливаем соединение с БД -> вынести перед методами
        2) формируется SQL запрос создания таблицы
        3) обработать возможные исключения
        */
            String sql= "CREATE TABLE IS NOT EXISTS user " +
                        "(id serial primary key," +
                        "name varchar(40)," +
                        "lastName varchar(40)," +
                        "age int)";
        try {
            Statement statement = connection.createStatement(); // создаём statement
            statement.executeUpdate(sql); //выполняем запрос SQL
        } catch (SQLException e) { //если отловили исключение
            throw new RuntimeException(e); //роняем программу
        }
    }

      /*       "(id INT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(45)," +
                "lastName VARCHAR(45)," +
                "age TINYINT(3))";
       */


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
