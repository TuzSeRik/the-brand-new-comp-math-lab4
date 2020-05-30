package io.github.tuzserik.utility;

public class GaussMethod {
    private double[][] matrix;

    public GaussMethod(double[][] matrix) {
        this.matrix = matrix;
    }

    public void forwardStroke() {
        double[][] firstMatrix = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, firstMatrix[i], 0, matrix[0].length);
        }

        for (int j = 0; j < matrix.length; j++) {
            int maxElemInColumn = maxElem(matrix, j);

            if (maxElemInColumn != j) {
                //noinspection ConstantConditions
                matrix = changeRows(matrix, j, maxElemInColumn);
            }

            for (int row = j + 1; row < matrix.length; row++) {
                double c = (matrix[row][j] / matrix[j][j]);
                matrix[row][j] = 0;

                for (int column = j + 1; column < matrix[0].length; column++) {
                    matrix[row][column] = matrix[row][column] - c * matrix[j][column];
                }
            }
        }
    }

    private double[] returnStroke() {
        double[] x = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            x[i] = 0;
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            double s = 0;

            for (int j = i + 1; j < matrix.length; j++) {
                s += matrix[i][j] * x[j];
            }

            x[i] = (matrix[i][matrix[0].length - 1] - s) / matrix[i][i];
        }

        return x;
    }

    private int maxElem(double[][] matrix, int column) {
        double max = 0;
        int maxElemInColumn = 0;

        for (int i = column; i < matrix.length; i++) {
            if (Math.abs(matrix[i][column]) - max > 0) {
                max = Math.abs(matrix[i][column]);
                maxElemInColumn = i;
            }
        }

        return maxElemInColumn;
    }

    private double[][] changeRows(double[][] matrix, int i, int maxElemInColumn) {
        double[] savingRow = new double[matrix[0].length];

        for (int j = 0; j < matrix[0].length; j++) {
            savingRow[j] = matrix[i][j];
            matrix[i][j] = matrix[maxElemInColumn][j];
            matrix[maxElemInColumn][j] = savingRow[j];
        }

        return matrix;
    }

    public double[] getAnswer() {
        forwardStroke();

        return returnStroke();
    }
}
