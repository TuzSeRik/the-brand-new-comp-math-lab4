package io.github.tuzserik.computing.functions;

import java.util.function.Function;

public abstract class ApproximationFunction {
    String name;
    Function<Double, Double> function;
    Double[] X;
    Double[] Y;
    int n;
    double[] functionValues;
    double S = 0;
    double delta;
    double a;
    double b;
    double c;

    public ApproximationFunction(Double[] X, Double [] Y) {
        this.X = X;
        this.Y = Y;
        n = X.length;
        functionValues = new double[n];
    }

    public String getName() {
        return name;
    }

    public Function<Double, Double> getFunction() {
        return function;
    }

    public double[] getFunctionValues() {
        for (int i = 0; i < n; i++) {
            functionValues[i] = Math.round(function.apply(X[i]) * 1000.0) / 1000.0;
            S = S + Math.pow(functionValues[i] - Y[i], 2);
        }
        delta = Math.sqrt(S / n);

        return functionValues;
    }

    public double getS() {
        return Math.round(S * 1000.0) / 1000.0;
    }

    public double getDelta() {
        return Math.round(delta * 1000.0) / 1000.0;
    }

    public double getA() {
        return Math.round(a * 1000.0) / 1000.0;
    }

    public double getB() {
        return Math.round(b * 1000.0) / 1000.0;
    }

    public double getC() {
        return Math.round(c * 1000.0) / 1000.0;
    }
}
