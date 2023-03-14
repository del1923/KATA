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

public class UserDaoJDBCImpl implements UserDao{


    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
    }

    public void dropUsersTable() {
    }

    public void saveUser( String name, String lastName, byte age) {
    }

    public void removeUserById(long id) {
    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {
    }
}
