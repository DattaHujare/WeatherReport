package com.weather.metoffice;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.weather.metoffice.adapter.YearRecyclerAdapter;
import com.weather.metoffice.widget.CustomTextViewRegular;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherYearActivity extends AppCompatActivity {

    @BindView(R.id.tv_toolbarTitle)
    CustomTextViewRegular tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvWeatherYear)
    RecyclerView rvWeatherYear;
    private String location;

    Context mContext;
    WeatherMetOfficeApp weatherMetOfficeApp;
    ArrayList<String> yearList = new ArrayList<>();
    YearRecyclerAdapter yearRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_year);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        mContext = WeatherYearActivity.this;
        weatherMetOfficeApp = (WeatherMetOfficeApp) getApplicationContext();

        if (getIntent().getStringExtra("location") != null) {
            location = getIntent().getStringExtra("location");
            tvToolbarTitle.setText(location);
        }

        for (int i = 1910; i <= 2017; i++) {
            yearList.add(String.valueOf(i));
        }

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(mContext, 4);
        rvWeatherYear.setLayoutManager(mLayoutManager);
        rvWeatherYear.setItemAnimator(new DefaultItemAnimator());
        yearRecyclerAdapter = new YearRecyclerAdapter(yearList, mContext, location);
        rvWeatherYear.setAdapter(yearRecyclerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
