package com.backpac.kjw.weatherapp.data.api

import com.backpac.kjw.weatherapp.data.domain.Location
import com.backpac.kjw.weatherapp.data.domain.Weather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * WeatherApp
 * Class: Apis
 * Created by JEONGWOOKIM on 2020-01-22.
 * Description: Apis for weather
 */
interface WeatherApi {
    @GET("/api/location/search/?query={query}")
    fun getLocations(@Path("query") query: String): Observable<Location>

    @GET("/api/location/{woeid}")
    fun getWeather(@Path("woeid") query: Int): Observable<Weather>
}