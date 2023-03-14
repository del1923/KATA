package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
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
        System.out.println("Сессия открыта");
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
        System.out.println();
    }

    @Override
    public void dropUsersTable() {

        final String SQL = "DROP TABLE IF EXISTS User"; //удаление таблицы
        System.out.println("Удаление таблицы");
        executeAndCheck(SQL);
        System.out.println(report);
        System.out.println();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        System.out.println("Добавление пользователя " + name);
        Session session = sessionFactory.openSession(); // открываем сессию
        System.out.println("Сессия открыта");
        Transaction transaction = session.beginTransaction(); // старт транзакции
//        User user = new User();
//        user.setName (name);
//        user.setLastName(lastName);
//        user.setAge(age);
        try {
            session.save(new User( name, lastName, age));
            System.out.println("Операция успешно выполнена");
            transaction.commit();
        } catch ( HibernateException e ) {
            e.printStackTrace();
            System.out.println("Ошибка, операция не выполнена");
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
            session.delete(session.get(User.class, id));
            System.out.println("Операция успешно выполнена");
            transaction.commit();
        } catch ( HibernateException e ) {
            e.printStackTrace();
            System.out.println("Ошибка, операция не выполнена");
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
        CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
        criteriaQuery.from(User.class);
        List<User> userList = session.createQuery(criteriaQuery).getResultList();
        try {
            transaction.commit();
            System.out.println("Операция успешно выполнена");
            return userList;
        } catch ( HibernateException e) {
            System.out.println("Ошибка, операция не выполнена");
            e.printStackTrace();
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
        System.out.println("Очистка таблицы");
        final String SQL = "TRUNCATE user"; //очищаем таблицу
        executeAndCheck( SQL );
        System.out.println(report);
        System.out.println();
    }
}
