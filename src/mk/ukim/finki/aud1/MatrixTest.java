package mk.ukim.finki.aud1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MatrixTest {

    // [[1,2,3,4], [5,6,7,8]]
    public double sumOfMatrix (double [][] matrix) {
        double sum = 0;
        for (int i=0;i<matrix.length;i++) {
            for (int j=0;j<matrix[i].length;j++) {
                sum+=matrix[i][j];
            }
        }
        //return sum;

        // [[1,2,3,4], [5,6,7,8]] -> [10, 26] -> 36
//        return IntStream.range(0, matrix.length)
//                .mapToDouble(i -> IntStream.range(0, matrix[0].length)
//                        .mapToDouble(j -> matrix[i][j]).sum())
//                .sum();

        // [[1,2,3,4], [5,6,7,8]] -> [1,2,3,4,5,6,7,8]
        return Arrays.stream(matrix)
                .flatMapToDouble(row -> Arrays.stream(row))
                .average()
                .getAsDouble();


    }

    public double averageOfMatrix (double [][] matrix) {
        return sumOfMatrix(matrix) / (matrix.length * matrix[0].length);
    }
}
