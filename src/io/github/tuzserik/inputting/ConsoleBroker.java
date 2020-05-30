package io.github.tuzserik.inputting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ConsoleBroker extends Broker{
    public ConsoleBroker(BufferedReader in, BufferedWriter out) {
        super(in, out);
    }

    @Override
    public ArrayList<ArrayList<Double>> readData() {
        ArrayList<ArrayList<Double>> returningArrays = new ArrayList<>();

        returningArrays.add(getParametersFromConsole("\nВведите все значения X в одну строчку через пробел.\n"));
        returningArrays.add(getParametersFromConsole("\nВведите все значения Y в одну строчку через пробел.\n"));

        return returningArrays;
    }

    @Override
    public void writeData(String answer) {
        try {
            out.write(answer);
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    ArrayList<Double> getParametersFromConsole(String text){
        ArrayList<Double> readParameters = new ArrayList<>();

        try {
            out.write(text);
            out.flush();
            String[] numbers = in.readLine().replace(",", ".").split(" ");

            for (String number: numbers) {
                readParameters.add(Double.parseDouble(number));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Введите числа правильно, используя пробел как разделитель и введите стоп-символ \"!\".");
        }

        return readParameters;
    }
}
