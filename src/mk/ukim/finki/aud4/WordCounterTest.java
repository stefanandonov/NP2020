package mk.ukim.finki.aud4;

import java.io.*;
import java.util.Scanner;
import java.util.function.Consumer;

class LineCounter {
    int lines;
    int words;
    int chars;

    public LineCounter(int lines, int words, int chars) {
        this.lines = lines;
        this.words = words;
        this.chars = chars;
    }

    public LineCounter sum (LineCounter other){
        return new LineCounter(
                this.lines+other.lines,
                this.words+other.words,
                this.chars+other.chars);
    }

    @Override
    public String toString() {
        return String.format("Lines: %d, Words: %d, Characters: %d", lines, words, chars);
    }
}

class LinesConsumer implements Consumer<String> {
    int lines;
    int words;
    int chars;

    @Override
    public void accept(String line) {
        lines++;
        words+=line.split("\\s+").length;
        chars+=line.length();
    }

    @Override
    public String toString() {
        return String.format("Lines: %d, Words: %d, Characters: %d", lines, words, chars);
    }
}

public class WordCounterTest {

    public static void wordCountWithScanner (InputStream inputStream) {
        int lines = 0, words = 0, chars = 0;
        Scanner sc = new Scanner(inputStream);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            ++lines;
            words+=line.split("\\s+").length;
            chars+=line.length();
        }

        System.out.printf("Lines: %d, Words: %d, Characters: %d", lines, words, chars);
    }

    public static void wordCountWithBufferedReader(InputStream inputStream) throws IOException {
        int lines = 0, words = 0, chars = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = br.readLine())!=null) {
            ++lines;
            words+=line.split("\\s+").length;
            chars+=line.length();
        }
        System.out.printf("Lines: %d, Words: %d, Characters: %d", lines, words, chars);
    }

    public static void wordCountWithBufferedReaderAndConsumer (InputStream inputStream){
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        LinesConsumer linesConsumer = new LinesConsumer();
        br.lines().forEach(linesConsumer);
        System.out.println(linesConsumer);
    }

    public static void wordCountWithMapReduce (InputStream inputStream) {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        LineCounter results = br.lines()
                .map(line -> new LineCounter(1, line.split("\\s+").length, line.length()))
                .reduce(
                        new LineCounter(0,0,0),
                        (l,r)-> l.sum(r)
                );

        System.out.println(results);
    }

    public static void main(String[] args) {
        File file = new File("/Users/stefanandonov/Documents/GitWorkspace/NP2020/src/mk/ukim/finki/aud4/data");
        try {
            wordCountWithScanner(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
