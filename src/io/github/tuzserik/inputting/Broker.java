package io.github.tuzserik.inputting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;

public abstract class Broker {
    BufferedReader in;
    BufferedWriter out;

    Broker(BufferedReader in, BufferedWriter out) {
        this.in = in;
        this.out = out;
    }

    public abstract ArrayList<ArrayList<Double>> readData();

    public abstract void writeData(String answer);
}
