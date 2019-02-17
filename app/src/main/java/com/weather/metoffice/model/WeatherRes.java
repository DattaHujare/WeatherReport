package com.weather.metoffice.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherRes {

    @SerializedName("value")
    @Expose
    private Double value;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("month")
    @Expose
    private Integer month;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }


    @Override
    public String toString() {
        return "WeatherRes{" +
                "value=" + value +
                ", year=" + year +
                ", month=" + month +
                '}';
    }
}
