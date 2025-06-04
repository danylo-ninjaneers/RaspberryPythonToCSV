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

        for (int i = 0; i < 100; i++) {
            LocalDate date = startDate.plusDays(i);
            double temp_min = Math.round((random.nextDouble() * 31 - 20) * 10.0) / 10.0; // -20.0 to 10.0
            double temp_max = Math.round((temp_min + random.nextDouble() * 20 + 5) * 10.0) / 10.0; // temp_min+5.0 to temp_min+25.0
            double percipitation = Math.round(random.nextDouble() * 500) / 10.0; // 0 to 50 mm
            double wind = Math.round((1 + (20 - 1) * random.nextDouble()) * 10.0) / 10.0; // 1 to 20 m/s
            String weather_type = WEATHER_TYPES[random.nextInt(WEATHER_TYPES.length)];

            dataList.add(new Datas(
                date.toString(),
                temp_min,
                temp_max,
                percipitation,
                wind,
                weather_type
            ));
        }
        return dataList;
    }
}