package jm.task.core.jdbc.util;

//import java.sql.Connection;
//import java.sql.Driver;
//import java.sql.DriverManager;
//import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import jm.task.core.jdbc.model.User;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class Util {
    /*
    проверить наличие нужного драйвера:
    сообщить название драйвера DRIVER

    проверить подключение к нашей БД:
    указать путь URL
    передать имя пользователя USER
    передать пароль PASSWORD

    создать подключение к БД для дальнейшего использования:
    URL
    USER
    PASSWORD

     */

    private static final String URL = "jdbc:mysql://localhost:3306/dbforjava";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static SessionFactory sessionFactory = null;

    /*
    static { //используем статический блок инициализации

        checkDriver();
    }

    private static void checkDriver() {
        try {
            Class.forName(DRIVER); //Проверяем наличие JDBC драйвера для работы с БД
            System.out.println("Есть драйвер для работы с БД");

        } catch (ClassNotFoundException e) {
            System.out.println("Нет драйвера для работы с БД");
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
    public static Connection cteateConnection() {
        try {
            return DriverManager.getConnection( URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Сбой подсключения к БД");
            throw new RuntimeException(e);
        }
    }
    */
    // описываем SessionFactory
    public static SessionFactory getConnection() {
         if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // прописываем настройки
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, DRIVER);
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USER);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "false");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;


    }


}







