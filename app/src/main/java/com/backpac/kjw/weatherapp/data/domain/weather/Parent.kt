package com.backpac.kjw.weatherapp.data.domain.weather

import com.google.gson.annotations.SerializedName

/**
 * WeatherApp
 * Class: Parent
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Parent of weather data
 */
data class Parent(
    @SerializedName("title") val title: String,
    @SerializedName("location_type") val location_type: String,
    @SerializedName("woeid") val woeid: Int,
    @SerializedName("latt_long") val latt_long: String
)