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
import org.hibernate.service.ServiceRegistry;

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

        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.connection.driver_class", DRIVER)
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", USER)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                    .addAnnotatedClass(User.class)
                    .setProperty("hibernate.c3p0.min_size","5")
                    .setProperty("hibernate.c3p0.max_size","200")
                    .setProperty("hibernate.c3p0.max_statements","200");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }


}







