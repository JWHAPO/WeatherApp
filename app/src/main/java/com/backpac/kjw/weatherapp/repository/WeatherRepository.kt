package com.backpac.kjw.weatherapp.repository

import com.backpac.kjw.weatherapp.data.api.WeatherApi
import com.backpac.kjw.weatherapp.extension.with
import io.reactivex.Observable

/**
 * WeatherApp
 * Class: WeatherRepository
 * Created by JEONGWOOKIM on 2020-02-18.
 * Description:
 */

class WeatherRepository(private val api: WeatherApi) {
    fun getWeathers(query: String) = api.getLocations(query).with()
        .flatMap { location ->
            Observable.fromIterable(location)
                .flatMap { weather ->
                    api.getWeather(weather.woeid).with()
                }
        }
        .toSortedList { t1, t2 -> t1.title.compareTo(t2.title) }!!

    fun getWeather(woeid: Int) = api.getWeather(woeid).with()
}