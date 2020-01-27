package com.backpac.kjw.weatherapp.data.domain.weather

import com.backpac.kjw.weatherapp.constant.Constants
import com.google.gson.annotations.SerializedName

/**
 * WeatherApp
 * Class: ConsolidatedWeather
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: ConsolidatedWeather of weather data
 */
class ConsolidatedWeather {
    @SerializedName("weather_state_name")
    val weather_state_name: String = ""

    @SerializedName("weather_state_abbr")
    val weather_state_abbr: String = ""

    @SerializedName("applicable_date")
    val applicable_date: String = ""

    @SerializedName("the_temp")
    val the_temp: Double = 0.0

    @SerializedName("humidity")
    val humidity: Int = 0
}