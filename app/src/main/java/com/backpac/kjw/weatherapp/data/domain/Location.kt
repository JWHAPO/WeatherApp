package com.backpac.kjw.weatherapp.data.domain

import com.google.gson.annotations.SerializedName

/**
 * WeatherApp
 * Class: Location
 * Created by JEONGWOOKIM on 2020-01-22.
 * Description: Location Data Class
 */
data class Location(
    @SerializedName("title") val title: String,
    @SerializedName("woeid") val woeid: Int
)
