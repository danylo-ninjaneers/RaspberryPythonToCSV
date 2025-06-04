package com.toCsv.csv;

public class Datas {
    private String date;
    private double temp_min;
    private double temp_max;
    private double percipitation;
    private double wind;
    private String weather_type;

    public Datas(String date, double temp_min, double temp_max, double percipitation, double wind, String weather_type) {
        this.date = date;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.percipitation = percipitation;
        this.wind = wind;
        this.weather_type = weather_type;
    }

    public String getDate() { return date; }
    public double getTemp_min() { return temp_min; }
    public double getTemp_max() { return temp_max; }
    public double getPercipitation() { return percipitation; }
    public double getWind() { return wind; }
    public String getWeather_type() { return weather_type; }
}