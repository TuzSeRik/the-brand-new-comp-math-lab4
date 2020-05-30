package io.github.tuzserik.computing.functions;

public class PowerFunction extends ApproximationFunction {
    private double SLNY = 0;
    private double SLNX = 0;
    private double SLNYLNX = 0;
    private double SLNXLNX = 0;

    public PowerFunction(Double[] X, Double [] Y) {
        super(X, Y);

        name = "Степенная функция";

        function = (Double x) -> a * Math.pow(x, b);

        for (int i = 0; i < n; i++) {
            SLNY = SLNY + Math.log(Y[i]);
            SLNX = SLNX + Math.log(X[i]);
            SLNYLNX = SLNYLNX + Math.log(Y[i]) * Math.log(X[i]);
            SLNXLNX = SLNXLNX + Math.log(X[i]) * Math.log(X[i]);
        }

        countA();
        countB();
    }

    private void countA() {
        double LNA = (SLNY * SLNXLNX - SLNYLNX * SLNX) / (n * SLNXLNX - SLNX * SLNX);
        a = Math.exp(LNA);
    }

    private void countB() {
        b = (n * SLNYLNX - SLNY * SLNX) / (n * SLNXLNX - SLNX * SLNX);
    }
}
