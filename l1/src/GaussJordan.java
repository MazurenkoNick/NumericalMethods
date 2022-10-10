public class GaussJordan {

    public static void main(String[] args) {
        double[][] matrix = {
                {3.81, 0.25, 1.28, 1.75, 4.21},
                {2.25, 1.32, 5.58, 0.49, 8.97},
                {5.31, 7.28, 0.98, 1.04, 2.38},
                {10.39, 2.45, 3.35, 2.28, 12.98}
        };
        System.out.println("Початкова матриця");
        printMatrix(matrix);
        GaussJordan method = new GaussJordan();
        method.solve(matrix);
        System.out.println("Кінцева діагональна матриця та вектор результатів");
        printMatrix(matrix);
    }

    private void reduceRow(double[] row, int idx) {
        // число, на яке буде ділитися весь ряд
        double number = row[idx];
        for (int i = 0; i < row.length; i++)
            // робимо число на головній діагоналі одиницею,
            // а інші числа просто ділимо на число з головної діагоналі
            row[i] /= number;
    }

    private void subtractRows(double[] row1, double[] row2, int columnIdx) {
        // число, на яке множиться весь від'ємник
        double multiplier = row1[columnIdx];

        for (int i = 0; i < row1.length; i++) {
            // процес віднімання строк
            row1[i] -= row2[i] * multiplier;
        }
    }

    public void solve(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            // ділимо всі числа на елемент діагоналі в цьому ряду
            System.out.printf(
                    "Ділимо строку: %d на%9.5f\n", i+1, matrix[i][i]
            );
            reduceRow(matrix[i], i);
            printMatrix(matrix);
            for (int j = 0; j < matrix[0].length-1; j++) {
                // пропускаємо ряд, з яким працювали
                if (j != i) {
                    System.out.printf(
                            "Від строки %d віднімаємо %d строку, помножену на %9.5f\n",
                            j+1, i+1, matrix[j][i]
                    );
                    subtractRows(matrix[j], matrix[i], i);
                    printMatrix(matrix);
                }
            }
        }
    }


    private static void printMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%9.5f",matrix[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
