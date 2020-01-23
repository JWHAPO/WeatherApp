package com.backpac.kjw.weatherapp.data.domain.weather

import com.google.gson.annotations.SerializedName

/**
 * WeatherApp
 * Class: ConsolidatedWeather
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: ConsolidatedWeather of weather data
 */
data class ConsolidatedWeather(
    @SerializedName("id") val id: Int,
    @SerializedName("weather_state_name") val weather_state_name: String,
    @SerializedName("weather_state_abbr") val weather_state_abbr: String,
    @SerializedName("wind_direction_compass") val wind_direction_compass: String,
    @SerializedName("created") val created: String,
    @SerializedName("applicable_date") val applicable_date: String,
    @SerializedName("min_temp") val min_temp: Double,
    @SerializedName("max_temp") val max_temp: Double,
    @SerializedName("the_temp") val the_temp: Double,
    @SerializedName("wind_speed") val wind_speed: Double,
    @SerializedName("wind_direction") val wind_direction: Double,
    @SerializedName("air_pressure") val air_pressure: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("visibility") val visibility: Double,
    @SerializedName("predictability") val predictability: Int
)