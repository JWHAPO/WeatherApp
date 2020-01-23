package com.backpac.kjw.weatherapp.di

import com.backpac.kjw.weatherapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * WeatherApp
 * Class: ViewModelModule
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: ViewModel Module
 */
val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}