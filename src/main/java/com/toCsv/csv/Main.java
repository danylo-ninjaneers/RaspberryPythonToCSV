package com.toCsv.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        String folderPath = "res";
        String csvFile = folderPath + "/output.csv";

        // Create the folder if it doesn't exist
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        List<Datas> lines = DataProvider.getData();

        try (FileWriter writer = new FileWriter(csvFile)) {
            // Write header
            //writer.write("date,temp_min,temp_max,percipitation,wind,weather_type\n");
            // Write data
            for (Datas d : lines) {
                writer.write(String.format(Locale.US, "%s,%.1f,%.1f,%.1f,%.1f,%s\n",
                d.getDate(),
                d.getTemp_min(),
                d.getTemp_max(),
                d.getPercipitation(),
                d.getWind(),
                d.getWeather_type()));
            }
            System.out.println("CSV file created successfully at " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}