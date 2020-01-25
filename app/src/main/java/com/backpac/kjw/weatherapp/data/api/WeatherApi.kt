package com.backpac.kjw.weatherapp.data.api

import com.backpac.kjw.weatherapp.data.domain.Location
import com.backpac.kjw.weatherapp.data.domain.weather.Weather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * WeatherApp
 * Class: Apis
 * Created by JEONGWOOKIM on 2020-01-22.
 * Description: Apis for weather
 */
interface WeatherApi {
    @GET("/api/location/search/")
    fun getLocations(@Query("query") query: String): Observable<List<Location>>

    @GET("/api/location/{woeid}")
    fun getWeathers(@Path("woeid") woeid: Int): Observable<Weather>
}