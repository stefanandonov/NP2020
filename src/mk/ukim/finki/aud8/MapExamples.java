package mk.ukim.finki.aud8;

import java.util.*;

public class MapExamples {

    private static void add (Map<String, String> map1,
                             Map<String, String> map2,
                             Map<String, String> map3,
                             Map<String, String> map4,
                             String key,
                             String value) {
        map1.put(key,value);
        map2.put(key,value);
        map3.put(key,value);
        map4.put(key,value);
    }

    public static void main(String[] args) {
        //K = username, V = password
        //1. HashMap, ne go zadrzuva redosledot na vnesuvanje
        Map<String,String> hashMap = new HashMap<>();

        //2. LinkedHashMap, go zadrzuva redosledot na vnesuvanje
        Map<String, String> linkedHashMap = new LinkedHashMap<>();

        //3 TreeMap, gi sortira KLUCEVITE spored compareTO na klasata Kluc ili spored nekoj komparator
        Map<String, String> treeMap = new TreeMap<>();

        Map<String, String> treeMap1 = new TreeMap<>(Comparator.comparing(String::length));

        add(hashMap, linkedHashMap, treeMap, treeMap1, "ZZZ", "12312423");
        add(hashMap, linkedHashMap, treeMap, treeMap1, "Stefan", "12345678");
        add(hashMap, linkedHashMap, treeMap, treeMap1, "Napredno programiranje", "password");
        add(hashMap, linkedHashMap, treeMap, treeMap1, "1234124", "blablabla123");
        add(hashMap, linkedHashMap, treeMap, treeMap1, "XYZ", "finki");
        add(hashMap, linkedHashMap, treeMap, treeMap1, "ZZZ", "finki123");

        System.out.println("HASH MAP");
        System.out.println(hashMap);
        System.out.println("LINKED HASH MAP");
        System.out.println(linkedHashMap);
        System.out.println("TREE MAP (by natural comparator");
        System.out.println(treeMap);
        System.out.println("TREE MAP (by length comparator");
        System.out.println(treeMap1);
    }
}
