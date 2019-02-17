package com.weather.metoffice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LocationSelectionActivity extends AppCompatActivity {

    @BindView(R.id.iv_location_uk)
    CardView ivLocationUk;
    @BindView(R.id.iv_location_england)
    CardView ivLocationEngland;
    @BindView(R.id.iv_location_scotland)
    CardView ivLocationScotland;
    @BindView(R.id.iv_location_wales)
    CardView ivLocationWales;
    Context mContext;
    WeatherMetOfficeApp weatherMetOfficeApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_selection);
        ButterKnife.bind(this);
        mContext = LocationSelectionActivity.this;
        weatherMetOfficeApp = (WeatherMetOfficeApp) getApplicationContext();
    }

    @OnClick(R.id.iv_location_uk)
    public void onIvLocationUkClicked() {
        startYearActivity(mContext.getResources().getString(R.string.uk));
    }

    @OnClick(R.id.iv_location_england)
    public void onIvLocationEnglandClicked() {
        startYearActivity(mContext.getResources().getString(R.string.england));
    }

    @OnClick(R.id.iv_location_scotland)
    public void onIvLocationScotlandClicked() {
        startYearActivity(mContext.getResources().getString(R.string.scotland));
    }

    @OnClick(R.id.iv_location_wales)
    public void onIvLocationWalesClicked() {
        startYearActivity(mContext.getResources().getString(R.string.wales));
    }


    public void startYearActivity(String location) {
        startActivity(new Intent(mContext, WeatherYearActivity.class).putExtra("location", location));
    }
}
