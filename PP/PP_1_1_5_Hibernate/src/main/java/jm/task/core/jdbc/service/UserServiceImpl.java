package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoJDBCImpl();
    private String name;
    private String lastName;
    private byte age;

    public void createUsersTable() {
        userDao.createUsersTable();
            }

    public void dropUsersTable() {
        userDao.dropUsersTable();
        }

    public void saveUser(String name,
                         String lastName,
                         byte age) {
        userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
    userDao.removeUserById( id );
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }
}
