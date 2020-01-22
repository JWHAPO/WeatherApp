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
    @SerializedName("location_type") val location_type: String,
    @SerializedName("latt_long") val latt_long: String,
    @SerializedName("woeid") val woeid: Int,
    @SerializedName("distance") val distance: Int
)
