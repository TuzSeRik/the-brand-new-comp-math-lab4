package io.github.tuzserik.inputting;

import java.io.*;
import java.util.ArrayList;

public class FileBroker extends Broker {
    public FileBroker(BufferedReader in, BufferedWriter out) {
        super(in, out);
    }

    @Override
    public ArrayList<ArrayList<Double>> readData() {
        ArrayList<ArrayList<Double>> returningArrays = new ArrayList<>();
        String path = System.getProperty("user.dir");
        String fileName = "";

        try {
            out.write("Введите имя файла, из которого хотите считать параметры (он должен лежать там же, где и исполняемая программа.\n");
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Что-то пошло не так, повторите попытку.");
        }

        try {
            File file = new File(path, fileName);
            BufferedReader reader = new BufferedReader(new FileReader(file));

            returningArrays.add(getParametersFromFile(reader));
            returningArrays.add(getParametersFromFile(reader));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Файл не найден!\nПроверьте правильность введенного имени файла или его наличие в папке.");
        }

        return returningArrays;
    }

    @Override
    public void writeData(String answer) {
        try (FileWriter writer = new FileWriter("answer.txt", false)) {
            writer.write(answer);
            writer.flush();
        }
        catch (IOException e) {
            System.out.println("Произошла ошибка записи в файл");
            e.printStackTrace();
        }
    }

    ArrayList<Double> getParametersFromFile(BufferedReader reader) {
        ArrayList<Double> readParameters = new ArrayList<>();

        try {
            String line = reader.readLine();
            if (line != null) {
                String[] numbers = line.replace(",", ".").split(" ");

                for (String number: numbers) {
                    readParameters.add(Double.parseDouble(number));
                }
            }
            else {
                System.out.println("Файл пуст!");
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка чтения из фалйа!");
            e.printStackTrace();
        }

        return readParameters;
    }
}
