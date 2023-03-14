package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

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
}







