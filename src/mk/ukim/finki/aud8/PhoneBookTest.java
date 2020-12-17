package mk.ukim.finki.aud8;

import java.util.*;
import java.util.stream.IntStream;

class DuplicateNumberException extends Exception {

    public DuplicateNumberException(String number) {
        super(String.format("Duplicate number: %s", number));
    }
}

class Contact {
    String name;
    String number;
    static Comparator<Contact> comparator = Comparator.comparing(Contact::getName)
            .thenComparing(Contact::getNumber);

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public List<String> getSubNumbers () {
        List<String> results = new ArrayList<>();
        for (int i=3;i<=number.length();i++) { //iterates the lengths of the subnumbers
            for (int j=0; j<=number.length()-i;j++) {
                results.add(number.substring(j, j+i));
            }
        }
        return results;
    }

    @Override
    public String toString() {
        return name + " " + number;
    }
}

class PhoneBook {

    Map<String, String> namesByPhoneNumbers;
    Map<String, Set<Contact>> contactsBySubNumbers;
    Map<String, Set<Contact>> contactsByName;

    PhoneBook() {
        namesByPhoneNumbers = new HashMap<>();
        contactsByName = new HashMap<>();
        contactsBySubNumbers = new HashMap<>();
    }

    public void addContact(String name, String number) throws DuplicateNumberException {
        if (namesByPhoneNumbers.containsKey(number))
            throw new DuplicateNumberException(number);

        namesByPhoneNumbers.put(number, name);

        Contact c = new Contact(name, number);

        c.getSubNumbers().forEach(
                subnumber -> {
                    contactsBySubNumbers.putIfAbsent(subnumber, new TreeSet<>(Contact.comparator));
                    contactsBySubNumbers.get(subnumber).add(c);
                }
        );

        contactsByName.putIfAbsent(name, new TreeSet<>(Contact.comparator));
        contactsByName.get(name).add(c);
    }

    public void contactsByNumber(String number) {
        if (contactsBySubNumbers.containsKey(number)) {
            contactsBySubNumbers.get(number)
                    .forEach(System.out::println);
        } else {
            System.out.println("NOT FOUND");
        }
    }

    public void contactsByName(String name) {
        if (contactsByName.containsKey(name)) {
            contactsByName.get(name)
                    .forEach(System.out::println);
        } else {
            System.out.println("NOT FOUND");
        }
    }

    public void printAllContactsSortedByPhoneNumber () {
        //K:number V:name
        Set<Map.Entry<String, String>> entrySet = namesByPhoneNumbers.entrySet();
//        for (Map.Entry<String,String> entry : entrySet)
//            System.out.println(entry.getKey() + "-->" + entry.getValue());

        entrySet.stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(entry.getKey() + "-->" + entry.getValue()));
    }
}

public class PhoneBookTest {

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; ++i) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            try {
                phoneBook.addContact(parts[0], parts[1]);
            } catch (DuplicateNumberException e) {
                System.out.println(e.getMessage());
            }
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            System.out.println(line);
            String[] parts = line.split(":");
            if (parts[0].equals("NUM")) {
                phoneBook.contactsByNumber(parts[1]);
            } else {
                phoneBook.contactsByName(parts[1]);
            }
        }
        phoneBook.printAllContactsSortedByPhoneNumber();
    }

}

// Вашиот код овде

