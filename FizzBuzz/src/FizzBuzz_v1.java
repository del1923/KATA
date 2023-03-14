
import java.util.Scanner;
import java.util.stream.IntStream;
/*
Правила игры FizzBuzz очень просты.
    Загадывается целое число до 100.
    Выводим на экран числа с 1 до загаданного.
    Скажите Fizz, если число делится на 3 .
    Произнесите Buzz, если число делится на 5 .
    Скажите FizzBuzz, если число делится и на 3, и на 5.
    Вернуть само число , если число не делится ни на 3, ни на 5.

 */

    class FizzBuzz_v1 {
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Введите целое число: ");
            int digit = scan.nextInt();
            scan.close();
            for (int i = 1; i <= digit; i++) {
                if (( i % 3 == 0) & ( i % 5 == 0)) {
                System.out.println("FizzBuzz");
                } else {
                    if ( i % 3 == 0) {
                        System.out.println("Fizz");
                    } else {
                        if ( i % 5 == 0) {
                            System.out.println("Buzz");
                        } else {
                            System.out.println( i );
                        }
                    }
                }
            }
        }
    }
    class FizzBuzz_v2 {
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Введите целое число: ");
            int digit = scan.nextInt();
            scan.close();
            IntStream.rangeClosed(1, digit)
                    .mapToObj(i -> i % 3 == 0 ? (i % 5 == 0 ? "FizzBuzz" : "Fizz") : (i % 5 == 0 ? "Buzz" : i+" "))
                    .forEach(System.out::println);
            }

    }
    class FizzBuzz_v3 {
        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Введите целое число: ");
            int digit = scan.nextInt();
            scan.close();

            StringBuilder str = new StringBuilder();
            for ( int i = digit ; i >= 1 ; i-- ) {
                if ( i % 5 == 0 ) {
                    str.append("Buzz");
                }
                if ( i % 3 == 0 ) {
                    str.append("Fizz");
                }
                if ( str.isEmpty() ) {
                    str.append( i );
                }
                System.out.println ( str );
                str.delete( 0 , 8 );
            }
        }
    }
