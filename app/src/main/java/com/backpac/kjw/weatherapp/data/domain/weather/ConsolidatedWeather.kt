package com.backpac.kjw.weatherapp.data.domain.weather

import com.google.gson.annotations.SerializedName

/**
 * WeatherApp
 * Class: ConsolidatedWeather
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: ConsolidatedWeather of weather data
 */
data class ConsolidatedWeather(
    @SerializedName("weather_state_name") val weather_state_name: String,
    @SerializedName("weather_state_abbr") val weather_state_abbr: String,
    @SerializedName("applicable_date") val applicable_date: String,
    @SerializedName("the_temp") val the_temp: Double,
    @SerializedName("humidity") val humidity: Int
)