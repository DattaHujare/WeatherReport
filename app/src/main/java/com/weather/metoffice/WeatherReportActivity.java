package com.weather.metoffice;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.weather.metoffice.adapter.WeatherReportAdapter;
import com.weather.metoffice.db.WeatherDataDbManager;
import com.weather.metoffice.dbmodel.WeatherData;
import com.weather.metoffice.widget.CustomTextViewMedium;
import com.weather.metoffice.widget.CustomTextViewRegular;
import com.weather.metoffice.widget.GalleryLayoutManager;
import com.weather.metoffice.widget.ScaleTransformer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherReportActivity extends AppCompatActivity {

    private static final String TAG = "WeatherReportActivity";
    @BindView(R.id.tv_toolbarTitle)
    CustomTextViewRegular tvToolbarTitle;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Context mContext;
    WeatherMetOfficeApp weatherMetOfficeApp;
    @BindView(R.id.rvReportCard)
    RecyclerView rvReportCard;
    @BindView(R.id.tvNoDataFound)
    CustomTextViewMedium tvNoDataFound;
    private String year;
    private String location;
    ArrayList<WeatherData> weatherDataList = new ArrayList<>();
    WeatherReportAdapter weatherReportAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_report);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        mContext = WeatherReportActivity.this;
        weatherMetOfficeApp = (WeatherMetOfficeApp) getApplicationContext();

        if (getIntent().getStringExtra("location") != null) {
            location = getIntent().getStringExtra("location");
            year = getIntent().getStringExtra("year");
            weatherDataList = (ArrayList<WeatherData>) WeatherDataDbManager.getInstance().getWeatherData(location, Integer.parseInt(year));
            tvToolbarTitle.setText(mContext.getResources().getString(R.string.weather_report) + " " + year);
        }

        GalleryLayoutManager layoutManager2 = new GalleryLayoutManager(GalleryLayoutManager.HORIZONTAL);
        layoutManager2.attach(rvReportCard, 0);
        layoutManager2.setCallbackInFling(true);
        layoutManager2.setItemTransformer(new ScaleTransformer());

        if (weatherDataList.size() != 0) {
            Log.e(TAG, "onCreate: " + weatherDataList);
            rvReportCard.setVisibility(View.VISIBLE);
            tvNoDataFound.setVisibility(View.GONE);
            weatherReportAdapter = new WeatherReportAdapter(weatherDataList, mContext);
            rvReportCard.setAdapter(weatherReportAdapter);
        } else {
            rvReportCard.setVisibility(View.GONE);
            tvNoDataFound.setVisibility(View.VISIBLE);
        }
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
