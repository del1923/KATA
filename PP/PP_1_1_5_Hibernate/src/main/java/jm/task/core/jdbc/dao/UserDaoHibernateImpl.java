package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    private final SessionFactory sessionFactory = Util.getConnection(); // получаем подключение через SessionFactory
    StringBuilder report = new StringBuilder();
    private StringBuilder executeAndCheck(String SQL) { //выполняем SQL и отлавливаем исключения
        Session session = sessionFactory.openSession(); // открываем сессию
        System.out.println("Сессия открыта");
        Transaction transaction = session.beginTransaction(); // старт транзакции
        try {
            session.createNativeQuery(SQL).executeUpdate();
            transaction.commit();
            report = report.append("Операция успешно выполнена");
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                report = report.append("Ошибка, отмена операции");
                transaction.rollback();
            }
        } finally {
            session.close();
            report = report.append(", cессия закрыта");
        }
        return report;
        }
    @Override
    public void createUsersTable() {
        System.out.println("Создание таблицы");
        executeAndCheck("CREATE TABLE IF NOT EXISTS User " +
                "(id int not null primary key auto_increment," +
                "name varchar(40) not null," +
                "lastname varchar(40)," +
                "age int)");
        System.out.println(report);
        report.delete(0, 45);
        System.out.println();
    }

    @Override
    public void dropUsersTable() { //удаление таблицы
        System.out.println("Удаление таблицы");
        executeAndCheck("DROP TABLE IF EXISTS User");
        System.out.println(report);
        report.delete(0, 45);
        System.out.println();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        System.out.println("Добавление пользователя " + name);
        Session session = sessionFactory.openSession(); // открываем сессию
        System.out.println("Сессия открыта");
        Transaction transaction = session.beginTransaction(); // старт транзакции
        try {
            session.save(new User( name, lastName, age));
            System.out.println("Операция успешно выполнена");
            transaction.commit();
        } catch ( HibernateException e ) {
            e.printStackTrace();
            if (transaction != null) {
                System.out.println("Ошибка, отмена операции");
                transaction.rollback();
            }
        } finally {
            session.close();
            System.out.println("Сессия закрыта");
            System.out.println();
        }
    }

    @Override
    public void removeUserById(long id) {
        System.out.println("Удаление пользователя по ID:" + id);
        Session session = sessionFactory.openSession(); // открываем сессию
        System.out.println("Сессия открыта");
        Transaction transaction = session.beginTransaction(); // старт транзакции
        try {
            Query query = session.createQuery("DELETE User WHERE id = :id");
            query.setParameter("id", id);
            int rows = query.executeUpdate();
            System.out.println("Удалено строк: " + rows);
            transaction.commit();
        } catch ( HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                System.out.println("Ошибка, отмена операции");
                transaction.rollback();
            }
        } finally {
            session.close();
            System.out.println("Сессия закрыта");
            System.out.println();
        }
    }

    @Override
    public List<User> getAllUsers() {
        System.out.println("Получение списка пользователей");
        Session session = sessionFactory.openSession(); // открываем сессию
        System.out.println("Сессия открыта");
        Transaction transaction = session.beginTransaction(); // старт транзакции
        List<User> userList = new ArrayList<>();
        try {
            userList = session.createQuery("FROM User", User.class).list();
            transaction.commit();
            System.out.println("Операция успешно выполнена");
            return userList;
        } catch ( HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                System.out.println("Ошибка, отмена операции");
                transaction.rollback();
            }
        } finally {
            session.close();
            System.out.println("Сессия закрыта");
            System.out.println();
        }
        System.out.println();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        System.out.println();
        System.out.println("Очистка таблицы"); //очищаем таблицу
        executeAndCheck( "TRUNCATE user" );
        System.out.println(report);
        report.delete(0, 45);
        System.out.println();
    }
}
