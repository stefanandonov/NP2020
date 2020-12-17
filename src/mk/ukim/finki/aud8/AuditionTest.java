package mk.ukim.finki.aud8;

import java.util.*;

class Participant {
    String code;
    String name;
    int age;

    public Participant(String code, String name, int age) {
        this.code = code;
        this.name = name;
        this.age = age;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("%s %s %d", code, name, age);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return code.equals(that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}

class Audition {

    Map<String, Map<String, Participant>> participantsByCity;
//    Map<String, Set<Participant>> participantsByCity;

    Audition() {
        participantsByCity = new HashMap<>();
    }

    public void addParticipant(String city, String code, String name, int age) {
        //1. Dokolku nema takov grad go dodavame za prv pat vo mapata
        participantsByCity.putIfAbsent(city, new HashMap<>());
        Participant p = new Participant(code, name, age);
        //2. Go dopolnuvame setot za toj grad so noviot ucesnik
//        Map<String, Participant> participantsByCode = participantsByCity.get(city);
//        if (!participantsByCode.containsKey(code)) {
//            participantsByCode.put(code, p);
//        }
        participantsByCity.computeIfPresent(city, (k, v) -> {
            v.putIfAbsent(code, p);
            return v;
        });
    }

    public void listByCity(String city) {
        participantsByCity.getOrDefault(city, new HashMap<>())
                .values()
                .stream()
                .sorted(Comparator.comparing(Participant::getName)
                        .thenComparing(Participant::getAge)
                        .thenComparing(Participant::getCode))
                .forEach(System.out::println);
    }
}

public class AuditionTest {
    public static void main(String[] args) {
        Audition audition = new Audition();
        List<String> cities = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(";");
            if (parts.length > 1) {
                audition.addParticipant(parts[0], parts[1], parts[2],
                        Integer.parseInt(parts[3]));
            } else {
                cities.add(line);
            }
        }
        for (String city : cities) {
            System.out.printf("+++++ %s +++++\n", city);
            audition.listByCity(city);
        }
        scanner.close();
    }
}