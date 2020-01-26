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
    @SerializedName("id")
    val id: Long = 0

    @SerializedName("weather_state_name")
    val weather_state_name: String = ""

    @SerializedName("weather_state_abbr")
    val weather_state_abbr: String = ""

    @SerializedName("wind_direction_compass")
    val wind_direction_compass: String = ""

    @SerializedName("created")
    val created: String = ""

    @SerializedName("applicable_date")
    val applicable_date: String = ""

    @SerializedName("min_temp")
    val min_temp: Double = 0.0

    @SerializedName("max_temp")
    val max_temp: Double = 0.0

    @SerializedName("the_temp")
    val the_temp: Double = 0.0

    @SerializedName("wind_speed")
    val wind_speed: Double = 0.0

    @SerializedName("wind_direction")
    val wind_direction: Double = 0.0

    @SerializedName("air_pressure")
    val air_pressure: Double = 0.0

    @SerializedName("humidity")
    val humidity: Int = 0

    @SerializedName("visibility")
    val visibility: Double = 0.0

    @SerializedName("predictability")
    val predictability: Int = 0

    val weather_state_icon: String
        get() = Constants.WEATHER_ICON_PATH.replace("X",weather_state_abbr)
}