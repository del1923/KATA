package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    Тут создаём один раз подключение и
    методы с запросами на таблицу user
 */


public class UserDaoJDBCImpl implements UserDao {

    Connection connection = Util.cteateConnection();//создаём подключение

    public UserDaoJDBCImpl() {
    }
    private void executeAndCheck(String SQL) { //выполняем SQL и отлавливаем исключения
        try {
            Statement statement = connection.createStatement(); // создаём statement
            statement.executeUpdate(SQL); //выполняем запрос SQL
        } catch (SQLException e) { //если отловили исключение
            throw new RuntimeException(e); //роняем программу
        }
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
        executeAndCheck( SQL );
    }

    public void dropUsersTable() {
        final String SQL = "DROP TABLE IF EXISTS User"; //удаление таблицы
        executeAndCheck( SQL );
    }

    public void saveUser( String name, String lastName, byte age) {
        final String SQL = "INSERT INTO User (name, lastname, age) VALUES ( ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setByte(3, age);
                statement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public void removeUserById(long id) {
        final String SQL = "DELETE FROM user WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(SQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        final String SQL = "SELECT * FROM user";
        List<User> listUser = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery(SQL);
            while (result.next()) {
                User user = new User(result.getString("name"),
                        result.getString("lastName"),
                        result.getByte("age"));
                user.setId(result.getLong("id"));
                listUser.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUser;
    }

    public void cleanUsersTable() {
        final String SQL = "TRUNCATE user"; //очищаем таблицу
        executeAndCheck( SQL );
    }
}
