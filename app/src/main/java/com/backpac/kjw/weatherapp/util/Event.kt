package com.backpac.kjw.weatherapp.util

/**
 * WeatherApp
 * Class: Event
 * Created by JEONGWOOKIM on 2020-02-05.
 * Description:
 */
open class Event<out T>(private val content: T) {
    private var hasBeenHandled = false

    fun getContentIfNotHandled(): T? {
        return if(hasBeenHandled){
            null
        }else{
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}