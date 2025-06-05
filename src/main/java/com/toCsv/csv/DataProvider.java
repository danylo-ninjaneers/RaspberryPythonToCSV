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

        double prevTemp = random.nextDouble() * 15 - 5; // Start between -5 and +10

        for (int i = 0; i < 100; i++) {
            LocalDate date = startDate.plusDays(i);

            // Gradual temperature change
            double tempChange = random.nextGaussian() * 2; // mean 0, stddev 2
            double temp_min = Math.max(-20, Math.min(25, prevTemp + tempChange));
            double temp_max = temp_min + 5 + random.nextDouble() * 10; // min+5 to min+15

            // Weather type based on temperature
            String weather_type;
            if (temp_max < 3) {
                weather_type = random.nextDouble() < 0.7 ? "snowy" : WEATHER_TYPES[random.nextInt(WEATHER_TYPES.length)];
            } else if (temp_min > 15) {
                weather_type = random.nextDouble() < 0.7 ? "sunny" : WEATHER_TYPES[random.nextInt(WEATHER_TYPES.length)];
            } else {
                weather_type = WEATHER_TYPES[random.nextInt(WEATHER_TYPES.length)];
            }

            // Precipitation based on weather type
            double percipitation = 0;
            switch (weather_type) {
                case "sunny":
                    percipitation = 0;
                    break;
                case "cloudy":
                    percipitation = Math.round(random.nextDouble() * 10) / 10.0;
                    break;
                case "rainy":
                    percipitation = Math.round((5 + random.nextDouble() * 45) * 10) / 10.0;
                    break;
                case "snowy":
                    percipitation = Math.round((2 + random.nextDouble() * 18) * 10) / 10.0;
                    break;
                case "windy":
                    percipitation = Math.round(random.nextDouble() * 5) / 10.0;
                    break;
                case "foggy":
                    percipitation = Math.round(random.nextDouble() * 2) / 10.0;
                    break;
                case "stormy":
                    percipitation = Math.round((10 + random.nextDouble() * 90) * 10) / 10.0;
                    break;
            }

            double wind = Math.round((1 + random.nextDouble() * 19) * 10.0) / 10.0;

            dataList.add(new Datas(
                date.toString(),
                Math.round(temp_min * 10.0) / 10.0,
                Math.round(temp_max * 10.0) / 10.0,
                percipitation,
                wind,
                weather_type
            ));

            prevTemp = temp_min + random.nextDouble() * (temp_max - temp_min);
        }
        return dataList;
    }
}