/*
Напишите метод, находящий в стриме минимальный и максимальный элементы в соответствии порядком, заданным Comparator'ом.

Найденные минимальный и максимальный элементы передайте в minMaxConsumer следующим образом:

minMaxConsumer.accept(min, max);

Если стрим не содержит элементов, то вызовите:

minMaxConsumer.accept(null, null);

Требования:
1. Должен быть метод public <T> void findMinMax(Stream<? extends T> stream, Comparator<? super T> order, BiConsumer<? super T, ? super T> minMaxConsumer)
2. Метод должен находить минимум и максимум в потоке, с помощью order.
3. Полученные данные должны быть записаны minMaxConsumer, согласно условию

 */
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Z_10_2_11 {
    public <T> void findMinMax(
            Stream<? extends T> stream,
            Comparator<? super T> order,
            BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> res = stream.sorted(order).collect(Collectors.toList()); //создаем стрим.сортируем(order).вЛист
        if (!res.isEmpty()) { //если коллекция не пуста
            minMaxConsumer.accept(res.get(0), res.get(res.size() - 1)); //возвращаем первый (MAX) и последний (MIN) элемент
        } else {
            minMaxConsumer.accept(null, null); //иначе возвращаем null, null
        }
    }

}