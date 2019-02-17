package com.weather.metoffice.api;


import com.weather.metoffice.model.WeatherRes;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface ApiService {
    @GET("interview-question-data/metoffice/{metric}-{location}.json")
    Single<List<WeatherRes>> WEATHER_RES_SINGLE(@Path("metric") String metric, @Path("location") String location);
}
