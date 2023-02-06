package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    /*
    Добавить создание подключения через SessionFactory
    Добавить переоределение методов как в JDBCImpl


     открыть сессию sessionFactory.openSession()
     старт транзакции из sessionFactory
     создать запрос через createNativeQuery
        если запрос успешен - коммит транзакци
        если не успешен или исключение - ролбек транзакции
        ЗАКРЫТЬ сессию
     */
    private final SessionFactory sessionFactory = Util.getConnection(); // получаем подключение через SessionFactory
    String report = "";
    private String executeAndCheck(String SQL) { //выполняем SQL и отлавливаем исключения
        Session session = sessionFactory.openSession(); // открываем сессию
        Transaction transaction = session.beginTransaction(); // старт транзакции
        try {session.createNativeQuery(SQL).executeUpdate();
            transaction.commit();
            report = "Операция успешно выполнена";
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                report = "Ошибка, отмена операции";
                transaction.rollback();
            }
        } finally {
            session.close();
            System.out.println("Сессия закрыта");
        }
        return report;
        }
    @Override
    public void createUsersTable() {
        final String SQL = "CREATE TABLE IF NOT EXISTS User " +
                "(id int not null primary key auto_increment," +
                "name varchar(40) not null," +
                "lastname varchar(40)," +
                "age int)";
        System.out.println("Создание таблицы");
        executeAndCheck(SQL);
        System.out.println(report);
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
        /*
        Session session = sessionFactory.openSession(); // открываем сессию
        Transaction transaction = session.beginTransaction(); // старт транзакции
        try {session.createNativeQuery(SQL).executeUpdate();
            transaction.commit();
            report = "Операция успешно выполнена";
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                report = "Ошибка, отмена операции";
                transaction.rollback();
            }
        } finally {
            session.close();
            System.out.println("Сессия закрыта");
        }
         */


        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
