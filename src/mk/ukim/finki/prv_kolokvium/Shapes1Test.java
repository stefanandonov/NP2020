package mk.ukim.finki.prv_kolokvium;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Square1 {
    int size;

    public Square1(int size) {
        this.size = size;
    }

    int perimeter() {
        return 4 * size;
    }
}

class Canvas1 implements Comparable<Canvas1> {
    String id;
    List<Square1> square1List;

    public Canvas1(String id, List<Square1> square1List) {
        this.id = id;
        this.square1List = square1List;
    }

    public static Canvas1 createCanvas(String line) {
        String[] parts = line.split("\\s+");
        String id = parts[0];
        List<Square1> square1s = Arrays.stream(parts)
                .skip(1)
                .map(part -> new Square1(Integer.parseInt(part)))
                .collect(Collectors.toList());
        return new Canvas1(id, square1s);
    }

    @Override
    public String toString() {
        return String.format(
                "%s %d %d",
                id,
                square1List.size(),
                square1List.stream()
                        .mapToInt(Square1::perimeter)
                        .sum()
        );
    }

    @Override
    public int compareTo(Canvas1 o) {
        return Integer.compare(
                this.square1List.stream().mapToInt(Square1::perimeter).sum(),
                o.square1List.stream().mapToInt(Square1::perimeter).sum()
        );
    }
}

class ShapesApplication1 {
    List<Canvas1> canvases;

    ShapesApplication1() {
        canvases = new ArrayList<>();
    }

    public int readCanvases(InputStream inputStream) {
        canvases = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .map(Canvas1::createCanvas)
                .collect(Collectors.toList());

        return canvases.stream()
                .mapToInt(c -> c.square1List.size())
                .sum();
    }

    public void printLargestCanvasTo(OutputStream outputStream) {
        PrintWriter pw = new PrintWriter(outputStream);
        Canvas1 max = canvases.stream()
                .max(Comparator.naturalOrder())
                .orElseGet(() -> new Canvas1("", Collections.emptyList()));
        pw.println(max.toString());
        pw.flush();
    }
}

public class Shapes1Test {

    public static void main(String[] args) {
        ShapesApplication1 shapesApplication = new ShapesApplication1();

        System.out.println("===READING SQUARES FROM INPUT STREAM===");
        shapesApplication.readCanvases(System.in);
        System.out.println("===PRINTING LARGEST CANVAS TO OUTPUT STREAM===");
        shapesApplication.printLargestCanvasTo(System.out);

    }
}
