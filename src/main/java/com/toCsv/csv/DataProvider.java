package com.toCsv.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;

public class DataProvider {
    private static final String[] WEATHER_TYPES = {
        "sunny", "cloudy", "rainy", "snowy", "windy", "foggy", "stormy"
    };

    public static List<Datas> getData() {
    List<Datas> dataList = new ArrayList<>();
    Random random = new Random();
    LocalDate startDate = LocalDate.now().minusDays(100);

    int daysInYear = 365;
    double avgTemp = 10; // average yearly temperature
    double amplitude = 15; // seasonal amplitude

    double prevTemp = avgTemp;

    for (int i = 0; i < 100; i++) {
        LocalDate date = startDate.plusDays(i);

        // Simulate seasonal temperature with sine wave
        double season = Math.sin(2 * Math.PI * (date.getDayOfYear() / (double)daysInYear));
        double baseTemp = avgTemp + amplitude * season;

        // Add small daily random variation
        double temp_min = baseTemp + random.nextGaussian() * 3 - 3;
        double temp_max = temp_min + 5 + random.nextDouble() * 7; // min+5 to min+12

        // Ensure temp_min < temp_max
        if (temp_max < temp_min) {
            double t = temp_min;
            temp_min = temp_max;
            temp_max = t;
        }

        // Weather type based on temperature
        String weather_type;
        if (temp_max < 3) {
            weather_type = random.nextDouble() < 0.8 ? "snowy" : "cloudy";
        } else if (temp_min > 18) {
            weather_type = random.nextDouble() < 0.7 ? "sunny" : "cloudy";
        } else {
            double r = random.nextDouble();
            if (r < 0.4) weather_type = "cloudy";
            else if (r < 0.6) weather_type = "rainy";
            else if (r < 0.7) weather_type = "windy";
            else if (r < 0.8) weather_type = "foggy";
            else if (r < 0.9) weather_type = "stormy";
            else weather_type = "sunny";
        }

        // Precipitation based on weather type
        double percipitation = 0;
        switch (weather_type) {
            case "sunny":
                percipitation = 0;
                break;
            case "cloudy":
                percipitation = Math.round(random.nextDouble() * 2 * 10) / 10.0;
                break;
            case "rainy":
                percipitation = Math.round((5 + random.nextDouble() * 15) * 10) / 10.0;
                break;
            case "snowy":
                percipitation = Math.round((2 + random.nextDouble() * 8) * 10) / 10.0;
                break;
            case "windy":
                percipitation = Math.round(random.nextDouble() * 2 * 10) / 10.0;
                break;
            case "foggy":
                percipitation = Math.round(random.nextDouble() * 1 * 10) / 10.0;
                break;
            case "stormy":
                percipitation = Math.round((10 + random.nextDouble() * 20) * 10) / 10.0;
                break;
        }

        double wind = Math.round((2 + random.nextDouble() * (weather_type.equals("stormy") ? 18 : 10)) * 10.0) / 10.0;

        dataList.add(new Datas(
            date.toString(),
            Math.round(temp_min * 10.0) / 10.0,
            Math.round(temp_max * 10.0) / 10.0,
            percipitation,
            wind,
            weather_type
        ));
    }
    return dataList;
}
}