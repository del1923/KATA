package jm.task.core.jdbc;


//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

/*      userService.createUsersTable();
        userService.saveUser("Василий", "Васин", (byte) 35);
        userService.saveUser("Пётр", "Петров", (byte) 23);
        userService.saveUser("Илья", "Ильин", (byte) 45);
        userService.saveUser("Дмитрий", "Дёмин", (byte) 41);
        userService.saveUser("Leo", "Doo", (byte) 37);
        userService.saveUser("Надя", "Надеждина", (byte) 19);
        userService.getAllUsers();
        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();


*/
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Василий", "Васин", (byte) 35);
        userService.saveUser("Пётр", "Петров", (byte) 23);
        userService.saveUser("Илья", "Ильин", (byte) 45);
        userService.saveUser("Дмитрий", "Дёмин", (byte) 41);
        userService.saveUser("Leo", "Doo", (byte) 37);
        userService.saveUser("Надя", "Надеждина", (byte) 19);

        userService.removeUserById(5L);
    }
}
