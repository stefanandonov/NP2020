package mk.ukim.finki.aud4;


import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Person implements Comparable<Person> {
    String name;
    int age;

    public Person (String inputLine) {
        String [] parts = inputLine.split("\\s+");
        this.name = parts[0];
        this.age = Integer.parseInt(parts[1]);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.age, o.age);
    }
}

public class OldestPersonTest {

    public static List<Person> readPeopleFrom (InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        return br.lines()
                .map(line -> new Person(line))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        File file = new File("/Users/stefanandonov/Documents/GitWorkspace/NP2020/src/mk/ukim/finki/aud4/licnosti");

        try {
            List<Person> persons = readPeopleFrom(new FileInputStream(file));

//            Person max = persons.get(0);
//            for (Person p : persons)
//                if (p.compareTo(max) > 0)
//                    max = p;
//
//            System.out.println(max);

            Person max = persons.stream()
                    .max(Comparator.naturalOrder())
                    .get();

//            Collections.sort(persons);
//            System.out.println(persons.get(persons.size()-1));

            System.out.println(max);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
