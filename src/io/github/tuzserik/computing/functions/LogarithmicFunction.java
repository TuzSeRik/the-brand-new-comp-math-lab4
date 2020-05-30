package io.github.tuzserik.computing.functions;

public class LogarithmicFunction extends ApproximationFunction {
    private double SY = 0;
    private double SLNX = 0;
    private double SLNXLNX = 0;
    private double SYLNX = 0;

    public LogarithmicFunction(Double[] X, Double[] Y) {
        super(X, Y);

        name = "Логарифмическая функция";

        function = (Double x) -> a * Math.log(x) + b;

        for (int i = 0; i < n; i++) {
            SY = SY + Y[i];
            SLNX = SLNX + Math.log(X[i]);
            SLNXLNX = SLNXLNX + Math.log(X[i]) * Math.log(X[i]);
            SYLNX = SYLNX + Y[i] * Math.log(X[i]);
        }

        countA();
        countB();
    }

    private void countA() {
        a = (n * SYLNX - SY * SLNX) / (n * SLNXLNX - SLNX * SLNX);
    }

    private void countB() {
        b = (SY * SLNXLNX - SYLNX * SLNX) / (n * SLNXLNX - SLNX * SLNX);
    }
}
