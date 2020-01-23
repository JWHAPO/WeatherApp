package com.backpac.kjw.weatherapp

import android.app.Application
import com.backpac.kjw.weatherapp.di.apiModule
import com.backpac.kjw.weatherapp.di.networkModule
import com.backpac.kjw.weatherapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * WeatherApp
 * Class: MainApplication
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Main Application
 */
@Suppress("Unused")
class MainApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(listOf(
                networkModule,
                apiModule,
                viewModelModule
            ))
        }
    }
}