package mk.ukim.finki.aud8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

class Book {
    String title;
    String category;
    float price;
//    static Comparator<Book> comparatorByTitleAndPrice = (l,r) -> {
//        int res = l.title.compareTo(r.title);
//        if (res==0) {
//            return Float.compare(l.price, r.price);
//        } else
//            return res;
//    };
//    static Comparator<Book> comparatorByPrice = (l,r) -> {
//        int res = Float.compare(l.price, r.price);
//        return res==0 ? l.title.compareToIgnoreCase(r.title) : res;
//    };

    static Comparator<Book> comparatorByTitleAndPrice = Comparator.comparing(Book::getTitle)
            .thenComparing(Book::getPrice);

    static Comparator<Book> comparatorByPrice = Comparator.comparing(Book::getPrice)
            .thenComparing(Book::getTitle);

    public Book(String title, String category, float price) {
        this.title = title;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        //A Brief History of Time (A) 25.66
        return String.format("%s (%s) %.2f", title, category, price);
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    //    @Override
//    public int compareTo(Book o) {
//        int res = this.title.compareTo(o.title);
//        if (res==0) {
//            return Float.compare(this.price, o.price);
//        } else
//            return res;
//    }
}

//class ComparatorByTitleAndPrice implements Comparator<Book> {
//
//    @Override
//    public int compare(Book o1, Book o2) {
//        int res = o1.title.compareTo(o2.title);
//        if (res==0) {
//            return Float.compare(o1.price, o2.price);
//        } else
//            return res;
//    }
//}

//class ComparatorByPrice implements Comparator<Book> {
//
//    @Override
//    public int compare(Book o1, Book o2) {
//        return Float.compare(o1.price, o2.price);
//    }
//}

class BookCollection {
    List<Book> books;

    BookCollection() {
        books = new ArrayList<>();
    }

    public void printByCategory(String category) {
//        books.sort(Book.comparatorByTitleAndPrice);
//        for (Book b : books) {
//            if (b.category.equalsIgnoreCase(category)) {
//                System.out.println(b);
//            }
//        }

        books.stream()
                .filter(b -> b.category.equalsIgnoreCase(category))
                .sorted(Book.comparatorByTitleAndPrice)
                .forEach(b -> System.out.println(b));

    }

    public List<Book> getCheapestN(int n) {
//        books.sort(Book.comparatorByPrice);
//        int end = Math.min(n, books.size());
//        return books.subList(0,end);

        return books.stream()
                .sorted(Book.comparatorByPrice)
                .limit(n)
                .collect(Collectors.toList());

    }

    public void addBook(Book book) {
        books.add(book);
    }
}

public class BooksTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        BookCollection booksCollection = new BookCollection();
        Set<String> categories = fillCollection(scanner, booksCollection);
        System.out.println("=== PRINT BY CATEGORY ===");
        for (String category : categories) {
            System.out.println("CATEGORY: " + category);
            booksCollection.printByCategory(category);
        }
        System.out.println("=== TOP N BY PRICE ===");
        print(booksCollection.getCheapestN(n));
    }

    static void print(List<Book> books) {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    static TreeSet<String> fillCollection(Scanner scanner,
                                          BookCollection collection) {
        TreeSet<String> categories = new TreeSet<String>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            Book book = new Book(parts[0], parts[1], Float.parseFloat(parts[2]));
            collection.addBook(book);
            categories.add(parts[1]);
        }
        return categories;
    }
}

// Вашиот код овде
