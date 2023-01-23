package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    /*
    подключиться к нашей БД:
    указать путь URL
    передать имя пользователя USER
    передать пароль PASSWORD
    сообщить название драйвера DRIVER
    проверить наличие нужного драйвера
    создать подключение
     */
    private static final String URL = "jdbc:mysql://localhost:3306/dbforjava";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    static {
        try {
            Class.forName(DRIVER); //Проверяем наличие JDBC драйвера для работы с БД
            System.out.println("Есть драйвер для работы с БД");

        } catch (ClassNotFoundException e) {
            System.out.println("Нет драйвера для работы с БД");
            System.out.println(e);
        }
    }

    public Connection connect; //создаём подключение к БД

    {
        try {
            connect = DriverManager.getConnection( URL, USER, PASSWORD ); //передавая адрес, пользователя, пароль
            System.out.println("Соединение с БД установлено");
        } catch (SQLException e) {
            System.out.println("Соединения с БД нет");
            throw new RuntimeException(e);
        }
    }
}







