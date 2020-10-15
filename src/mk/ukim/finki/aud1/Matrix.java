package mk.ukim.finki.aud1;

public class Matrix {

    private static double matrixSum(double[][] matrix){
        double sum = 0;
        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                sum += matrix[i][j];
            }
        }
        return sum;
    }

    private static double matrixAverage(double[][] matrix){
        return matrixSum(matrix) / (matrix.length * matrix[0].length);
    }

    public static void main(String[] args) {

        double[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}};

        System.out.println("The sum of the matrix is: " + matrixSum(matrix));
        System.out.println("The average value of the matrix is: " + matrixAverage(matrix));
    }
}
