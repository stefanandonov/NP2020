package mk.ukim.finki.aud5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

interface Drawable{
    String draw();
}

class GenericDrawableBox<T extends Drawable & Comparable<T>> {
    List<T> elements;
    static Random rdm = new Random();
    GenericDrawableBox() {
        elements = new ArrayList<>();
    }

    void add (T element) {
        elements.add(element);
        //Collections.sort(elements);
    }

    boolean isEmpty () {
        return elements.size()==0;
    }

    void drawElement () {
        if (isEmpty())
            return ;

        //int idx = rdm.nextInt(elements.size());
        //System.out.println(String.format("Index %d called", idx));
        Collections.sort(elements);
        System.out.println(elements.remove(elements.size()-1).draw());
    }
}

class DrawableAndComparable implements Drawable, Comparable<DrawableAndComparable> {

    int number;

    public DrawableAndComparable(int number) {
        this.number = number;
    }

    @Override
    public int compareTo(DrawableAndComparable o) {
        return Integer.compare(this.draw().length(),o.draw().length());
    }

    @Override
    public String draw() {
        return BoxDrawableTest.numberToStars(number);
    }
}

public class BoxDrawableTest {

    public static String numberToStars (int number) {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<number;i++)
            sb.append("*");
        return sb.toString();
    }
    public static void main(String[] args) {
        GenericDrawableBox<DrawableAndComparable> genericDrawableBox = new GenericDrawableBox<>();
        Random rdm = new Random();
        for (int i=0;i<100;i++) {
            DrawableAndComparable d =  new DrawableAndComparable(rdm.nextInt(25)+1);
            genericDrawableBox.add(d);
        }

        for (int i=0;i<110;i++) {
            genericDrawableBox.drawElement();
        }

    }
}