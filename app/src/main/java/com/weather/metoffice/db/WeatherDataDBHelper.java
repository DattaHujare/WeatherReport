package com.weather.metoffice.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.weather.metoffice.dbmodel.WeatherData;


public class WeatherDataDBHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "WeatherDataDBHelper.sqlite";
    private static final String TAG = "WeatherDataDBHelper";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;
    private static Dao<WeatherData, Integer> weatherDao = null;

    public WeatherDataDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, WeatherData.class);
        } catch (SQLException e) {
            e.printStackTrace();
            Log.e(TAG, "onCreate: " + e.getMessage());
            Log.e(WeatherDataDBHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            /**
             * Recreates the database when onUpgrade is called by the framework
             */
            TableUtils.dropTable(connectionSource, WeatherData.class, false);
            onCreate(database, connectionSource);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public Dao<WeatherData, Integer> getWeatherData() throws SQLException {
        if (weatherDao == null) {
            try {
                weatherDao = getDao(WeatherData.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return weatherDao;
    }
}
