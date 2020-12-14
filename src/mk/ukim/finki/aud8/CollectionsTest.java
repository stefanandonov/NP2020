package mk.ukim.finki.aud8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionsTest {

    public static int count(Collection<Collection<String>> c, String str) {
//        int count = 0;
//        for (Collection<String> collection : c){
//            for (String s : collection) {
//                if (s.equalsIgnoreCase(str)) {
//                    ++count;
//                }
//            }
//        }
//        return count;

//        return (int) c.stream()
//                .flatMap(collection -> collection.stream())
//                .filter(s -> s.equalsIgnoreCase(str))
//                .count();

        return c.stream()
                .flatMapToInt(collection -> collection.stream()
                        .mapToInt(s -> s.equalsIgnoreCase(str) ? 1 : 0)
                )
                .sum();


    }

    public static void main(String[] args) {
        Collection<Collection<String>> collection = new ArrayList<>();

        List<String> list1 = List.of("1", "11", "123");
        List<String> list2 = List.of("11", "111", "123");
        List<String> list3 = List.of("1", "111", "113");

        collection.add(list1);
        collection.add(list2);
        collection.add(list3);

        System.out.println(count(collection, "11"));

    }
}
