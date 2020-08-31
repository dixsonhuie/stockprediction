package com.gigaspaces.demo.feeder;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.Random;

// Range 11-95
// 91.5775, 81.5, 84, 73.2, 79.59
public class CreateCsvData {

    private static String FILENAME = "data.csv";

    private static int generateRandom(int start, int range) {
        Random rand = new Random();
        int r =  rand.nextInt(range) + start;
        return r;
    }
    private static float generateRandom(int start, int range, double places) {
        Random rand = new Random();
        int n = (int) Math.pow(10d, places);
        int r =  rand.nextInt(range * n) + (start * n);

        System.out.println("r is: " + r);
        return (float) r/n;
    }
    private static boolean generateRandomBoolean() {
        Random rand = new Random();
        return rand.nextBoolean();
    }

    public static void main(String[] args) throws Exception {

        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        FileWriter fileWriter = new FileWriter(FILENAME);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for(int i=0; i< 100; i++) {
            LocalDate date = today.minusDays(i);
            String formattedDate = date.format(formatter);
            String symbol = "SPY";
            String upOrDown = ((generateRandomBoolean() == false) ? "DN" : "UP");
            float colA = generateRandom(11, 84, 4);
            float colB = generateRandom(11, 84, 1);
            int colC   = generateRandom(11, 84);
            float colD = generateRandom(11, 84, 1);
            float colE = generateRandom(11, 84, 2);

            printWriter.printf("%s, %s, %s, %.4f, %.1f, %d, %.1f, %.2f%n", formattedDate, symbol, upOrDown, colA, colB, colC, colD, colE);

        }
        printWriter.close();
    }
}
