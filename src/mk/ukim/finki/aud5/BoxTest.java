package mk.ukim.finki.aud5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class GenericBox<T> {
    List<T> elements;
    static Random rdm = new Random();
    GenericBox() {
        elements = new ArrayList<>();
    }

    void add (T element) {
        elements.add(element);
    }

    boolean isEmpty () {
        return elements.size()==0;
    }

    T drawElement () {
        if (isEmpty())
            return null;

        int idx = rdm.nextInt(elements.size());
        //System.out.println(String.format("Index %d called", idx));
        return elements.remove(idx);
    }
}

public class BoxTest {

    public static void main(String[] args) {
        GenericBox<Integer> integerGenericBox = new GenericBox<>();
        Random random = new Random();
        for (int i=0;i<150;i++) {
            integerGenericBox.add(random.nextInt(50));
        }

        for (int i=0;i<200;i++) {
            System.out.println(integerGenericBox.drawElement());
        }


    }
}
