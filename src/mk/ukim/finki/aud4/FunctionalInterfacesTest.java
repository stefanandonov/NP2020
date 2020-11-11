package mk.ukim.finki.aud4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfacesTest {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>();
        //Supplier with annonimous class
//        Supplier<Integer> integerSupplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                return new Random().nextInt();
//            }
//        };
        //Supplier with lambda expression
        Supplier<Integer> integerSupplier = () -> new Random().nextInt();

        //Consumer with a. class -- foreach statement .stream()
        Consumer<Integer> integerConsumer = integer -> {
            numbers.add(integer);
            System.out.println(integer);
        };

        //Predicate filter/condition .filter .anyMatch .allMatch
        Predicate<Integer> lessThenFive = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer<5;
            }
        };

        Predicate<Integer> lessThenFiveLambda = integer -> integer<5;

        //Function (.map) (flatMap) 5 -> 5.00
        Function<Integer, String> integerToStringMapper = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return String.valueOf(integer.doubleValue());
            }
        };

        Function<Integer, String> integerToStringMapperLambda = integer -> String.valueOf(integer.doubleValue());

    }

}
