package com.weather.metoffice.db;

import android.content.Context;
import android.database.SQLException;
import android.util.Log;

import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.weather.metoffice.dbmodel.WeatherData;

import java.util.List;


public class WeatherDataDbManager {

    private static final String TAG = "WeatherManager";
    static private WeatherDataDbManager instance;
    private WeatherDataDBHelper helper;

    private WeatherDataDbManager(Context ctx) {
        helper = new WeatherDataDBHelper(ctx);
    }

    static public void init(Context ctx) {
        if (null == instance) {
            instance = new WeatherDataDbManager(ctx);
        }
    }

    static public WeatherDataDbManager getInstance() {
        return instance;
    }

    private WeatherDataDBHelper getHelper() {
        return helper;
    }


    /***
     *
     * DataBase Operation for WeatherData
     * @return
     */


    public List<WeatherData> getAllWeatherData() {
        List<WeatherData> weatherDataList = null;
        try {
            weatherDataList = getHelper().getWeatherData().queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherDataList;
    }

    public String getWeatherDataItemCount() {
        List<WeatherData> weatherDataList = null;
        try {
            weatherDataList = getHelper().getWeatherData().queryForAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int count = 0;
        if (weatherDataList != null) {
            for (WeatherData weatherData :
                    weatherDataList) {
                count += weatherData.getId();
            }
        }
        return String.format("%02d", count);
    }

    public void addWeatherData(WeatherData weatherData) {
        try {
            getHelper().getWeatherData().create(weatherData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteWeatherData(int tripId) {
        try {
            DeleteBuilder<WeatherData, Integer> deleteBuilder = getHelper().getWeatherData().deleteBuilder();
            deleteBuilder.where().eq("id", tripId);
            deleteBuilder.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateWeatherTminData(double tMin, String location, int month, int year) {
        try {
            UpdateBuilder<WeatherData, Integer> updateBuilder = getHelper().getWeatherData().updateBuilder();
            updateBuilder.where().eq("location", location)
                    .and().eq("month", month)
                    .and().eq("year", year);
            updateBuilder.updateColumnValue("tMin" /* column */, tMin /* value */);
            updateBuilder.update();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }


    }


    public void updateWeatherRainfallData(double rainFall, String location, int month, int year) {
        try {
            UpdateBuilder<WeatherData, Integer> updateBuilder = getHelper().getWeatherData().updateBuilder();
            updateBuilder.where().eq("location", location)
                    .and().eq("month", month)
                    .and().eq("year", year);
            updateBuilder.updateColumnValue("rainFall" /* column */, rainFall /* value */);
            updateBuilder.update();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();

        }


    }


    public List<WeatherData> getWeatherData(String location, int year) {
        List<WeatherData> weatherData = null;
        try {
            weatherData = getHelper().getWeatherData().queryBuilder()
                    .where()
                    .eq("location", location)
                    .and()
                    .eq("year", year)
                    .query();

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            Log.e(TAG, "getWeatherData: " + e.getMessage());
        }
        return weatherData;
    }


    public void deleteWeatherDataItemAll() {
        try {
            DeleteBuilder<WeatherData, Integer> deleteBuilder = getHelper().getWeatherData().deleteBuilder();
            deleteBuilder.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void refreshWeatherData(WeatherData weatherData) {
        try {
            getHelper().getWeatherData().refresh(weatherData);
        } catch (SQLException | java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


}
