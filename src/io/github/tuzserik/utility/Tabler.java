package io.github.tuzserik.utility;

import java.util.ArrayList;

public class Tabler {
    private final ArrayList<ArrayList<String>> table = new ArrayList<>();
    private final ArrayList<Integer> columnWidth = new ArrayList<>();


    public void addColumn(String columnTitle) {
        ArrayList<String> column = new ArrayList<>();
        column.add(columnTitle);
        columnWidth.add(columnTitle.length());
        table.add(column);
    }

    public void addRow(String... rowValues) {
        for (int i = 0; i < table.size(); i++) {
            table.get(i).add(rowValues[i]);
        }
    }

    public void printTable() {
        System.out.print("\n\n\n\n");

        for (int size : columnWidth) {
            for (int i = 0; i < size + 2; i++)
                System.out.print("—");
        }

        for (int i = 0; i < 7; i++) {
            System.out.print("—");
        }

        for (int i = 0; i < table.get(0).size(); i++) {
            System.out.print("\n|\n|");

            for (int j = 0; j < table.size(); j++) {
                String temp = (" " + table.get(j).get(i) + " ");

                while (temp.length() < columnWidth.get(j)+2) {
                    //noinspection StringConcatenationInLoop
                    temp += " ";
                }

                System.out.print(temp + "|");
            }

            for (int size : columnWidth) {
                for (int k = 0; k < size + 2; k++)
                    System.out.print(" ");
            }

            System.out.print("\n");

            for (int size : columnWidth) {
                for (int l = 0; l < size + 2; l++)
                    System.out.print("-");
            }

            for (int n = 0; n < 7; n++) {
                System.out.print("—");
            }
        }

        System.out.print("\n\n");
    }
}
