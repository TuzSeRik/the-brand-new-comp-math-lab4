package io.github.tuzserik.computing;

import io.github.tuzserik.computing.functions.*;
import io.github.tuzserik.inputting.Broker;
import io.github.tuzserik.utility.Charter;
import io.github.tuzserik.utility.Tabler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Method {
    Broker broker;
    Charter charter;
    Tabler table;

    LinearFunction linearFunction;
    QuadraticFunction quadraticFunction;
    ExponentialFunction exponentialFunction;
    LogarithmicFunction logarithmicFunction;
    PowerFunction powerFunction;

    Double[] xArray;
    Double[] yArray;

    double[] specialXArray;
    double[] specialYArray;

    public Method(Broker broker) {
        this.broker = broker;
        ArrayList<ArrayList<Double>> temp = broker.readData();
        xArray = new Double[temp.get(0).size()];
        yArray = new Double[temp.get(0).size()];

        specialXArray = new double[temp.get(0).size()];
        specialYArray = new double[temp.get(0).size()];

        for (int i = 0; i < temp.get(0).size(); i++) {
            xArray[i] = temp.get(0).get(i);
            yArray[i] = temp.get(1).get(i);

            specialXArray[i] = temp.get(0).get(i);
            specialYArray[i] = temp.get(1).get(i);
        }

        linearFunction = new LinearFunction(xArray, yArray);
        quadraticFunction = new QuadraticFunction(xArray, yArray);
        exponentialFunction = new ExponentialFunction(xArray, yArray);
        logarithmicFunction = new LogarithmicFunction(xArray, yArray);
        powerFunction = new PowerFunction(xArray, yArray);
    }

    public void showSeries() {
        charter = new Charter("Аппроксимация функции методом наименьших квадратов", Collections.min(Arrays.asList(xArray)), Collections.max(Arrays.asList(xArray)));
        charter.addPoints(specialXArray, specialYArray);
        charter.generateSeries("F=ax+b", linearFunction.getFunction());
        charter.generateSeries("F=ax^2+bx+c", quadraticFunction.getFunction());
        charter.generateSeries("F=ae^bx", exponentialFunction.getFunction());
        charter.generateSeries("F=alnx+b", logarithmicFunction.getFunction());
        charter.generateSeries("F=ax^b", powerFunction.getFunction());
        charter.show();
    }

    public void printTable() {
        table = new Tabler();

        table.addColumn("Вид φ(х)        ");
        table.addColumn("Линейная        ");
        table.addColumn("Полиномиальная  ");
        table.addColumn("Экспоненциальная");
        table.addColumn("Логарифмическая ");
        table.addColumn("Степенная       ");

        table.addRow("X | Y", "F=ax+b", "F=ax^2+bx+c", "F=ae^bx", "F=alnx+b", "F=ax^b");

        for (int i = 0; i < xArray.length; i++) {
            table.addRow(xArray[i] + " | " + yArray[i],
                    String.valueOf(linearFunction.getFunctionValues()[i]),
                    String.valueOf(quadraticFunction.getFunctionValues()[i]),
                    String.valueOf(exponentialFunction.getFunctionValues()[i]),
                    String.valueOf(logarithmicFunction.getFunctionValues()[i]),
                    String.valueOf(powerFunction.getFunctionValues()[i]));
        }

        table.addRow("S",
                String.valueOf(linearFunction.getS()),
                String.valueOf(quadraticFunction.getS()),
                String.valueOf(exponentialFunction.getS()),
                String.valueOf(logarithmicFunction.getS()),
                String.valueOf(powerFunction.getS()));

        table.addRow("δ",
                String.valueOf(linearFunction.getDelta()),
                String.valueOf(quadraticFunction.getDelta()),
                String.valueOf(exponentialFunction.getDelta()),
                String.valueOf(logarithmicFunction.getDelta()),
                String.valueOf(powerFunction.getDelta()));

        table.addRow("a",
                String.valueOf(linearFunction.getA()),
                String.valueOf(quadraticFunction.getA()),
                String.valueOf(exponentialFunction.getA()),
                String.valueOf(logarithmicFunction.getA()),
                String.valueOf(powerFunction.getA()));

        table.addRow("b",
                String.valueOf(linearFunction.getB()),
                String.valueOf(quadraticFunction.getB()),
                String.valueOf(exponentialFunction.getB()),
                String.valueOf(logarithmicFunction.getB()),
                String.valueOf(powerFunction.getB()));

        table.addRow("c",
                String.valueOf(linearFunction.getC()),
                String.valueOf(quadraticFunction.getC()),
                String.valueOf(exponentialFunction.getC()),
                String.valueOf(logarithmicFunction.getC()),
                String.valueOf(powerFunction.getC()));

        table.printTable();
    }

    public String findBestFunction() {
        String functionName = "error";
        double min = Collections.min(Arrays.asList(
                linearFunction.getDelta(),
                quadraticFunction.getDelta(),
                exponentialFunction.getDelta(),
                logarithmicFunction.getDelta(),
                powerFunction.getDelta()));

        if (min == linearFunction.getDelta()) functionName = linearFunction.getName();
        if (min == quadraticFunction.getDelta()) functionName = quadraticFunction.getName();
        if (min == exponentialFunction.getDelta()) functionName = exponentialFunction.getName();
        if (min == logarithmicFunction.getDelta()) functionName = logarithmicFunction.getName();
        if (min == powerFunction.getDelta()) functionName = powerFunction.getName();

        return "Лучшая аппроксимирующая функция это: " + functionName;
    }
}
