package com.weather.metoffice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.weather.metoffice.api.ApiRequestHelper;
import com.weather.metoffice.api.ApiService;
import com.weather.metoffice.db.WeatherDataDbManager;
import com.weather.metoffice.dbmodel.WeatherData;
import com.weather.metoffice.model.WeatherRes;
import com.weather.metoffice.utils.Utils;
import com.weather.metoffice.widget.materialprogressbar.CustomProgressDialog;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    Context context;
    WeatherMetOfficeApp weatherMetOfficeApp;
    CustomProgressDialog cpd;

    /**
     * Main View
     */

    ImageView ivOuterCircle;
    MaterialRippleLayout ivAccept;
    TextView tv;
    ProgressBar circularProgressbar;
    RelativeLayout rlMain;


    final int[] pStatus = {0};

    private CompositeDisposable disposable = new CompositeDisposable();
    private ApiService apiService;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = MainActivity.this;
        WeatherDataDbManager.init(this);
        WeatherDataDbManager.getInstance().deleteWeatherDataItemAll();
        weatherMetOfficeApp = (WeatherMetOfficeApp) getApplicationContext();

        ivOuterCircle = findViewById(R.id.iv_outer_circle);
        ivAccept = findViewById(R.id.iv_accept);
        tv = findViewById(R.id.tv);
        circularProgressbar = findViewById(R.id.circularProgressbar);
        rlMain = findViewById(R.id.rl_main);

        cpd = new CustomProgressDialog(context);
        disposable = new CompositeDisposable();
        apiService = ApiRequestHelper.getClient(context).create(ApiService.class);
        getWeatherDataForTmax("Tmax", "UK");

    }

    public void getWeatherDataForTmax(String metric, final String location) {

        // cpd.show();
        disposable.add(
                apiService.WEATHER_RES_SINGLE(metric, location).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<WeatherRes>>() {
                            @Override
                            public void onSuccess(List<WeatherRes> res) {
                                if (res.size() != 0) {
                                    for (int i = 0; i < res.size(); i++) {
                                        WeatherData weatherData = new WeatherData();
                                        weatherData.settMax(res.get(i).getValue());
                                        weatherData.setMonth(res.get(i).getMonth());
                                        weatherData.setYear(res.get(i).getYear());
                                        weatherData.setLocation(location);
                                        WeatherDataDbManager.getInstance().addWeatherData(weatherData);
                                        //      cpd.dismiss();
                                    }
                                    setPogress();
                                    getWeatherDataForTmin("Tmin", location);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                //    cpd.dismiss();
                                Utils.showErrorToast(context, e.getMessage());
                                Log.e(TAG, "onError: " + e.getMessage());
                            }
                        }));
    }


    public void getWeatherDataForTmin(String metric, final String location) {
        //cpd.show();
        disposable.add(
                apiService
                        .WEATHER_RES_SINGLE(metric, location)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<WeatherRes>>() {
                            @Override
                            public void onSuccess(List<WeatherRes> res) {
                                if (res.size() != 0) {
                                    for (int i = 0; i < res.size(); i++) {
                                        double value = res.get(i).getValue();
                                        int month = res.get(i).getMonth();
                                        int year = res.get(i).getYear();
                                        WeatherDataDbManager.getInstance().updateWeatherTminData(value, location, month, year);

                                    }
                                    // cpd.dismiss();
                                    setPogress();
                                    getWeatherDataForRainFall("Rainfall", location);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                //cpd.dismiss();
                                Utils.showErrorToast(context, e.getMessage());
                                Log.e(TAG, "onError: " + e.getMessage());
                            }
                        }));
    }


    public void getWeatherDataForRainFall(String metric, final String location) {
        // cpd.show();
        disposable.add(
                apiService
                        .WEATHER_RES_SINGLE(metric, location)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<WeatherRes>>() {
                            @Override
                            public void onSuccess(List<WeatherRes> res) {

                                if (res.size() != 0) {

                                    for (int i = 0; i < res.size(); i++) {
                                        double value = res.get(i).getValue();
                                        int month = res.get(i).getMonth();
                                        int year = res.get(i).getYear();
                                        WeatherDataDbManager.getInstance().updateWeatherRainfallData(value, location, month, year);
                                    }

                                    setPogress();
                                    if (location.equalsIgnoreCase("UK"))
                                        getWeatherDataForTmax("Tmax", "England");

                                    if (location.equalsIgnoreCase("England"))
                                        getWeatherDataForTmax("Tmax", "Scotland");

                                    if (location.equalsIgnoreCase("Scotland"))
                                        getWeatherDataForTmax("Tmax", "Wales");

                                    if (location.equalsIgnoreCase("Wales")) {
                                        startActivity(new Intent(context, LocationSelectionActivity.class));
                                        finish();
                                    }
                                    // cpd.dismiss();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                //cpd.dismiss();
                                Utils.showErrorToast(context, e.getMessage());
                                Log.e(TAG, "onError: " + e.getMessage());
                            }
                        }));
    }

    public void setPogress() {
        pStatus[0] += 8;
        circularProgressbar.setProgress(pStatus[0]);
    }
}
