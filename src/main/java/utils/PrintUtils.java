package utils;

public class PrintUtils {
    public static void printArray(int[] array) {
        System.out.print('{');
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) System.out.print(", ");
        }
        System.out.println('}');
    }

    public static void printMatrix(int[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            System.out.print('{');
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
                if (j != matrix[i].length - 1) System.out.print(", ");
            }
            System.out.println('}');
        }

    }
}
