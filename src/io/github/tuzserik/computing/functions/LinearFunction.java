package io.github.tuzserik.computing.functions;

public class LinearFunction extends ApproximationFunction {
    private double SX = 0;
    private double SXX = 0;
    private double SY = 0;
    private double SXY = 0;

    public LinearFunction(Double[] X, Double[] Y) {
        super(X, Y);

        name = "Линейная функция";

        function = (Double x) -> a * x + b;

        for (int i = 0; i < n; i++) {
            SX = SX + X[i];
            SXX = SXX + X[i] * X[i];
            SY = SY + Y[i];
            SXY = SXY + X[i] * Y[i];
        }

        countA();
        countB();
    }

    private void countA() {
        super.a = (SXY * n - SX * SY) / (SXX * n - SX * SX);
    }

    private void countB() {
        super.b = (SXX * SY - SX * SXY) / (SXX * n - SX * SX);
    }
}
