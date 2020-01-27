package com.backpac.kjw.weatherapp.util

import java.time.LocalDate


/**
 * WeatherApp
 * Class: WeatherAdapter
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Weather list adapter
 */
object DateUtil{

    fun getToday(): String{
        return LocalDate.now().toString()
    }

    fun getTomorrow(): String{
        return LocalDate.now().plusDays(1).toString()
    }

}