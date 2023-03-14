import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

 /*
 Всего 3 класса и одна зависимость.
Класс App содержит метод main, который запускает Спринг при создании контекста. В контекст мы передаем класс
AppConfig, который помечен аннотацией @Configuration - так Спринг понимает, что это настроечный класс.

В классе AppConfig содержится метод, помеченный аннотацией как бин, этот метод будет выполнен при запуске и
его результат станет объектом, управляемым Спрингом.

Класс HelloWorld является обычным Java-классом, мы его используем без каких-либо сложностей.
В методе main мы можем получить бин HelloWorld по имени и использовать в любом месте программы.
Спринг позволяет использовать различные типы бинов, одни будут жить от старта до завершения программы, другие
 будут создаваться при каждом запросе или открытии новой сессии, третьи при каждом вызове будут создаваться
 новые. За это отвечает аннотация @Scope.
  */

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld bean =
                (HelloWorld) applicationContext.getBean("helloworld");
        HelloWorld bean1 =
                (HelloWorld) applicationContext.getBean("helloworld");

        Cat bean3 = (Cat) applicationContext.getBean("Cat");
        Cat bean4 = (Cat) applicationContext.getBean("Cat");
        System.out.print( "При сравнении HelloWorld = HelloWorld -> ");
        System.out.println( bean == bean1 );
        System.out.print( "При сравнении Cat = Cat -> ");
        System.out.println( bean3 == bean4 );
    }
}