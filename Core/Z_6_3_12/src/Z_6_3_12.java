public class Z_6_3_12 {
    /*
    Создайте класс Human с полями возраст (age), имя (name), фамилия (secondName) и любимый вид спорта (favoriteSport).

Создайте 3 конструктора в классе Human, 1-ый - пустой, 2-ой, который конструирует Human’a от всех полей и третий, который конструирует Human’a, от всех полей, кроме любимого вида спорта. Для успешного тестирования решения важно объявлять аргументы в конструкторах именно в том порядке, в котором они написаны в данном описании (возраст (age), имя (name), фамилия (secondName) и любимый вид спорта (favoriteSport))

В методе main создайте 3 экземпляра человека используя 3 этих разных конструктора. поля класса должны иметь размерность, не превышающую необходимую. Класс должен быть объявлен статическим, метод main не должен находиться в классе, поля класса должны иметь доступ только внутри класса
Требования:
1. Должен быть метод public static void main(String[] args)
2. Должен быть public static class Human
3. У класс Human должны быть поля
4. Класс Human должен иметь 3 конструктора
5. В методе main нужно создать три объекта класса Human тремя способами

     */
        public static void main(String[] args) {
            Human humanFirst = new Human();                                             //создаётся дефолтным
            Human humanSecond = new Human( (byte) 30, "Vasija", "Pupkin", "jumping" );  //создаётся полным
            Human humanThird = new Human( (byte) 25, "Petro", "Vasin" );                //создаётся без спорта
        }
        public static class Human {         //определяем класс Human
            private byte age;                       //количество лет НЕ БОЛЕЕ 127
            private String name;                    //имя
            private String secondName;              //фамилия
            private String favoriteSport;           //любимый спорт

            public Human() {
                //КОНСТРУКТОР 1 дефолтный
            }
            public Human(byte age, String name, String secondName, String favoriteSport) { //КОНСТРУКТОР 2 полный
                this(age, name, secondName); //вызываем конструктор с тремя параметрами
                this.favoriteSport = favoriteSport; //добавляем четвёртый параметр

            }
            public Human(byte age, String name, String secondName) {                      //КОНСТРУКТОР 3 без спорта
                this.age = age;
                this.name = name;
                this.secondName = secondName;
            }
        }


}