package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    /*
    Добавить создание подключения через SessionFactory
    Добавить переоределение методов как в JDBCImpl

     */
    private final SessionFactory sessionFactory = Util.getConnection(); // получаем подключение через SessionFactory
    @Override
    public void createUsersTable() {
        /*
        открыть сессию sessionFactory.openSession()
        старт транзакции из sessionFactory
        создать запрос через createNativeQuery
            если запрос успешен - коммит транзакции
            если не успешен или исключение - ролбек транзакции
            ЗАКРЫТЬ сессию
         */
        Session session = sessionFactory.openSession(); // открываем сессию
        Transaction transaction = session.beginTransaction(); // старт транзакции
        /*
        final String SQL = "CREATE TABLE IF NOT EXISTS User " +
                "(id int not null primary key auto_increment," +
                "name varchar(40) not null," +
                "lastname varchar(40)," +
                "age int)";
        executeAndCheck( SQL );
         */

    }

    @Override
    public void dropUsersTable() {

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
