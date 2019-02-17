package com.weather.metoffice.dbmodel;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "WheatherData")
public class WeatherData {
    @DatabaseField
    String location;
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private double tMax;

    @DatabaseField
    private double tMin;

    @DatabaseField
    private double rainFall;

    @DatabaseField
    private int year;

    @DatabaseField
    private int month;


    public WeatherData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double gettMax() {
        return tMax;
    }

    public void settMax(double tMax) {
        this.tMax = tMax;
    }

    public double gettMin() {
        return tMin;
    }

    public void settMin(double tMin) {
        this.tMin = tMin;
    }

    public double getRainFall() {
        return rainFall;
    }

    public void setRainFall(double rainFall) {
        this.rainFall = rainFall;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "location='" + location + '\'' +
                ", id=" + id +
                ", tMax=" + tMax +
                ", tMin=" + tMin +
                ", rainFall=" + rainFall +
                ", year=" + year +
                ", month=" + month +
                '}';
    }
}