package com.backpac.kjw.weatherapp.repository

import com.backpac.kjw.weatherapp.data.api.WeatherApi
import com.backpac.kjw.weatherapp.extension.with

/**
 * WeatherApp
 * Class: WeatherRepository
 * Created by JEONGWOOKIM on 2020-02-18.
 * Description:
 */

class WeatherRepository(private val api: WeatherApi) {
    fun getLocations(query: String) =  api.getLocations(query).with()
    fun getWeather(woeid: Int) =  api.getWeather(woeid).with()
}