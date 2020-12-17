package mk.ukim.finki.aud8;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class File implements Comparable<File> {
    String name;
    int size;
    LocalDateTime createdAt;
    private final Comparator<File> comparator = Comparator.comparing(File::getCreatedAt)
            .thenComparing(File::getName)
            .thenComparing(File::getSize);


    public File(String name, int size, LocalDateTime createdAt) {
        this.name = name;
        this.size = size;
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public int compareTo(File o) {
        return comparator.compare(this, o);
    }

    public boolean isHiddenFileWithSizeLessThen(int size) {
        return name.startsWith(".") && this.size < size;
    }

    public String createMonthAndDayString() {
        return createdAt.getMonth().toString() + "-" + createdAt.getDayOfMonth();
    }

    @Override
    public String toString() {
        return String.format("%-10s %5dB %s", name, size, createdAt);
    }
}

class FileSystem {

    Map<Character, Set<File>> filesByFolders;
    Map<String, Long> sumOfFilesByMonthAndDay;
    Set<File> allFiles;

    FileSystem() {
        filesByFolders = new HashMap<>();
        allFiles = new TreeSet<>();
    }

    public void addFile(char folder, String name, int size, LocalDateTime createdAt) {
        File f = new File(name, size, createdAt);

        allFiles.add(f);

        filesByFolders.putIfAbsent(folder, new TreeSet<>());
        filesByFolders.get(folder).add(f);

    }

    public List<File> findAllHiddenFilesWithSizeLessThen(int size) {
        return allFiles.stream()
                .filter(f -> f.isHiddenFileWithSizeLessThen(size))
                .collect(Collectors.toList());
    }

    public int totalSizeOfFilesFromFolders(List<Character> folders) {
        return folders.stream()
                .flatMapToInt(
                        folder -> filesByFolders.getOrDefault(folder, new TreeSet<>())
                                .stream()
                                .mapToInt(File::getSize))
                .sum();

    }

    public Map<Integer, Set<File>> byYear() {
        return allFiles.stream()
                .collect(Collectors.groupingBy(
                        f -> f.getCreatedAt().getYear(),
                        TreeMap::new,
                        Collectors.toCollection(TreeSet::new)
                ));
    }

    public Map<String, Long> sizeByMonthAndDay() {
        return allFiles.stream()
                .collect(Collectors.groupingBy(
                        File::createMonthAndDayString,
                        TreeMap::new,
                        Collectors.summingLong(File::getSize)
                ));
    }
}

/**
 * Partial exam II 2016/2017
 */
public class FileSystemTest {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(":");
            fileSystem.addFile(parts[0].charAt(0), parts[1],
                    Integer.parseInt(parts[2]),
                    LocalDateTime.of(2016, 12, 29, 0, 0, 0).minusDays(Integer.parseInt(parts[3]))
            );
        }
        int action = scanner.nextInt();
        if (action == 0) {
            scanner.nextLine();
            int size = scanner.nextInt();
            System.out.println("== Find all hidden files with size less then " + size);
            List<File> files = fileSystem.findAllHiddenFilesWithSizeLessThen(size);
            files.forEach(System.out::println);
        } else if (action == 1) {
            scanner.nextLine();
            String[] parts = scanner.nextLine().split(":");
            System.out.println("== Total size of files from folders: " + Arrays.toString(parts));
            int totalSize = fileSystem.totalSizeOfFilesFromFolders(Arrays.stream(parts)
                    .map(s -> s.charAt(0))
                    .collect(Collectors.toList()));
            System.out.println(totalSize);
        } else if (action == 2) {
            System.out.println("== Files by year");
            Map<Integer, Set<File>> byYear = fileSystem.byYear();
            byYear.keySet().stream().sorted()
                    .forEach(key -> {
                        System.out.printf("Year: %d\n", key);
                        Set<File> files = byYear.get(key);
                        files.stream()
                                .sorted()
                                .forEach(System.out::println);
                    });
        } else if (action == 3) {
            System.out.println("== Size by month and day");
            Map<String, Long> byMonthAndDay = fileSystem.sizeByMonthAndDay();
            byMonthAndDay.keySet().stream().sorted()
                    .forEach(key -> System.out.printf("%s -> %d\n", key, byMonthAndDay.get(key)));
        }
        scanner.close();
    }
}

// Your code here

