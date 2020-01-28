package com.backpac.kjw.weatherapp.ui.main

import com.backpac.kjw.weatherapp.data.domain.weather.Weather
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock
import java.util.*
import kotlin.math.roundToInt

/**
 * WeatherApp
 * Class: MainBindingAdapterTest
 * Created by JEONGWOOKIM on 2020-01-28.
 * Description:
 */
class MainBindingAdapterTest {

    @Test
    fun addListHeader() {
        val headerItem: Weather = mock(Weather::class.java)
        val listItem1: Weather = mock(Weather::class.java)
        val listItem2: Weather = mock(Weather::class.java)

        assertNotSame(headerItem, listItem1)

        val weatherItems: LinkedList<Weather> = LinkedList(listOf(listItem1, listItem2))
        weatherItems.push(headerItem)

        assertSame(headerItem, weatherItems.first)
    }

    @Test
    fun convertTemp() {
        var value: Double = 3.4999
        assertEquals(value.roundToInt().toString(), "2")
        value = 2.5001
        assertEquals(value.roundToInt().toString(), "3")
    }
}