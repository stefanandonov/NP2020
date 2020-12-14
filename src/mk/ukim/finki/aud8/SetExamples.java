package mk.ukim.finki.aud8;

import java.util.*;

public class SetExamples {

    private static void addToSet(Set<String> set1,
                                 Set<String> set2,
                                 Set<String> set3,
                                 Set<String> set4,
                                 String element) {
        set1.add(element);
        set2.add(element);
        set3.add(element);
        set4.add(element);
    }

    public static void main(String[] args) {
        //1. HashSet, ne go zapazuva redosledot. Redosledot se utvrduva spored hash()
        Set<String> hashSet = new HashSet<>();

        //2. LinkedHashSet, GO zapozuva redosledot
        Set<String> linkedHashSet = new LinkedHashSet<>();

        //3. TreeSet, gi sortira vrednosti leksikografski
        Set<String> treeSet = new TreeSet<>();

        //4. TreeSet, gi sortira stringovite spored dolzinata
        Set<String> treeSet1 = new TreeSet<>(Comparator.comparingInt(String::length).reversed());

        addToSet(hashSet, linkedHashSet, treeSet, treeSet1, "ZZZ");
        addToSet(hashSet, linkedHashSet, treeSet, treeSet1, "Stefan");
        addToSet(hashSet, linkedHashSet, treeSet, treeSet1, "Napredno programiranje");
        addToSet(hashSet, linkedHashSet, treeSet, treeSet1, "1234124");
        addToSet(hashSet, linkedHashSet, treeSet, treeSet1, "XYZ");

        System.out.println("HASH SET");
        System.out.println(hashSet);
        System.out.println("LINKED HASH SET");
        System.out.println(linkedHashSet);
        System.out.println("TREE SET (by natural comparator");
        System.out.println(treeSet);
        System.out.println("TREE SET (by length comparator");
        System.out.println(treeSet1);


    }
}
