package mk.ukim.finki.aud5;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Random;

public class MyMathClass {
    //min: 5 max: 10 avg: 7.5 sum: 75 std:
//    public static <T extends Number> String statistics (List<T> numbers) {
//
//    }

    public static String statistics (List<? extends Number> numbers) {
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        double sum = 0.0;
        for (Number n : numbers) {
            double value = n.doubleValue();
            sum+=value;
            if (value>max) {
                max = value;
            }
            if (value<min) {
                min = value;
            }
        }
        int count = numbers.size();
        double avg = sum / count;

        double sumForStd = 0.0;
        for (Number n : numbers) {
            double value = n.doubleValue();
            sumForStd += ((value-avg)*(value-avg));
        }

        double std = Math.sqrt(sumForStd/count);

        return String.format("min: %.2f\nmax: %.2f\navg: %.2f\nstd: %.2f\nsum: %.2f\ncount: %d",
                min,
                max,
                avg,
                std,
                sum,
                count);

    }

    public static String statisticsWithStreams (List<? extends Number> numbers) {
        double min = numbers.stream().mapToDouble(n -> n.doubleValue()).min().getAsDouble();
        double max = numbers.stream().mapToDouble(n -> n.doubleValue()).max().getAsDouble();
        double avg = numbers.stream().mapToDouble(n -> n.doubleValue()).average().getAsDouble();
        double sum = numbers.stream().mapToDouble(n -> n.doubleValue()).sum();
        int count = numbers.size();

        double sumForStd = 0.0;
        for (Number n : numbers) {
            double value = n.doubleValue();
            sumForStd += ((value-avg)*(value-avg));
        }

        double std = Math.sqrt(sumForStd/count);

        return String.format("min: %.2f\nmax: %.2f\navg: %.2f\nstd: %.2f\nsum: %.2f\ncount: %d",
                min,
                max,
                avg,
                std,
                sum,
                count);

    }

    public static String statisticsWithSummaryStatistics (List<? extends Number> numbers) {

        DoubleSummaryStatistics summary = numbers.stream().mapToDouble(n -> n.doubleValue()).summaryStatistics();

        double sumForStd = 0.0;
        for (Number n : numbers) {
            double value = n.doubleValue();
            sumForStd += ((value-summary.getAverage())*(value-summary.getAverage()));
        }

        double std = Math.sqrt(sumForStd/summary.getCount());

        return String.format("min: %.2f\nmax: %.2f\navg: %.2f\nstd: %.2f\nsum: %.2f\ncount: %d",
                summary.getMin(),
                summary.getMax(),
                summary.getAverage(),
                std,
                summary.getSum(),
                summary.getCount());

    }





    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        Random rdm = new Random();
        for (int i=0;i<10000000;i++) {
            integers.add(rdm.nextInt(100)+1);
        }

        System.out.println(statistics(integers));
    }
}
