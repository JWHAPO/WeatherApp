package com.backpac.kjw.weatherapp.data.domain.weather

import com.google.gson.annotations.SerializedName

/**
 * WeatherApp
 * Class: Sources
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Sources of weather data
 */
data class Sources(
    @SerializedName("title") val title: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("url") val url: String,
    @SerializedName("crawl_rate") val crawl_rate: Int
)