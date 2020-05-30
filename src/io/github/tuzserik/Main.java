package io.github.tuzserik;

import io.github.tuzserik.computing.Method;
import io.github.tuzserik.inputting.Broker;
import io.github.tuzserik.inputting.ConsoleBroker;
import io.github.tuzserik.inputting.FileBroker;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

        Broker currentBroker = null;
        Method currentMethod;

        try {
            out.write("Для работы с консолью введите \"1\".\n\n");

            out.write("Для работы с файлом введите \"2\".\n"
                    + "В файле в качестве разделителя должны быть использованы пробелы.\n\n");
            out.flush();

            switch (in.readLine()) {
                case "1":
                    currentBroker = new ConsoleBroker(in, out);
                    break;
                case "2":
                    currentBroker = new FileBroker(in, out);
                    break;
            }

            assert currentBroker != null;
            currentMethod = new Method(currentBroker);

            currentMethod.showSeries();
            currentMethod.printTable();

            out.write(currentMethod.findBestFunction());
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка потока данных!\nПроверьте, скорее всего вы ввели неправильные данные.");
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("Вы не ввели данные, необходимые для старта программы!\nПовторите попытку ввода.");
        }
    }
}
