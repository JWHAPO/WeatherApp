package com.backpac.kjw.weatherapp.di

import com.backpac.kjw.weatherapp.repository.WeatherRepository
import org.koin.dsl.module

/**
 * WeatherApp
 * Class: repositoryModule
 * Created by JEONGWOOKIM on 2020-02-19.
 * Description: Repository Module
 */

val repositoryModule = module {
    single(createdAtStart = false) { WeatherRepository(get()) }
}