package com.weather.metoffice;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.weather.metoffice.db.WeatherDataDbManager;
import com.weather.metoffice.utils.ConnectionDetector;

import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "Splash_Activity";
    WeatherMetOfficeApp weatherMetOfficeApp;
    Context mContext;
    ConnectionDetector cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        weatherMetOfficeApp = (WeatherMetOfficeApp) getApplicationContext();
        mContext = SplashActivity.this;
        cd = new ConnectionDetector(mContext);


    }

    @Override
    protected void onResume() {
        super.onResume();

        GotoActivity();

    }

    private void GotoActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (WeatherDataDbManager.getInstance().getAllWeatherData() != null && WeatherDataDbManager.getInstance().getAllWeatherData().size() != 0)
                    startActivity(new Intent(mContext, LocationSelectionActivity.class));
                else
                    startActivity(new Intent(mContext, MainActivity.class));
                finish();
            }
        }, 2000);
    }
}
