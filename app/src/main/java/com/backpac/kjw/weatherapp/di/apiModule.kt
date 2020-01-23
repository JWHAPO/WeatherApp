package com.backpac.kjw.weatherapp.di

import com.backpac.kjw.weatherapp.data.api.WeatherApi
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * WeatherApp
 * Class: apiModule
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: API Module
 */
val apiModule = module {
    single(createdAtStart = false) { get<Retrofit>().create(WeatherApi::class.java) }
}