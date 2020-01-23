package com.backpac.kjw.weatherapp.util

import androidx.lifecycle.MutableLiveData

/**
 * WeatherApp
 * Class: NotNullMutableLiveData
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description:Not Null LiveData
 */
class NotNullMutableLiveData<T : Any>(defaultValue: T) : MutableLiveData<T>() {

    init {
        value = defaultValue
    }

    override fun getValue() = super.getValue()!!
}