import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
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