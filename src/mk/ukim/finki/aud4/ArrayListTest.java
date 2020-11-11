package mk.ukim.finki.aud4;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {

    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();
        List<Integer> biggerArray = new ArrayList<>();

        biggerArray.add(2);
        biggerArray.add(3);


        integers.add(5); //na posledna lokacija
        System.out.println(integers.size());

        integers.add(0, 7);
        integers.add(1, 8);

        System.out.println(integers); // [1, 2, 3, 4, 5]

        System.out.println(integers.contains(4));
        System.out.println(integers.contains(5));

        biggerArray.addAll(integers);

        System.out.println(biggerArray);



    }
}
