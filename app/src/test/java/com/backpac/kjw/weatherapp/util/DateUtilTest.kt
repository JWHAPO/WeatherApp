package com.backpac.kjw.weatherapp.util

import android.os.Build
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

/**
 * WeatherApp
 * Class: DateUtilTest
 * Created by JEONGWOOKIM on 2020-01-28.
 * Description: Date Util Test
 */
class DateUtilTest {
    private lateinit var today: String
    private lateinit var tomorrow: String

    @Before
    fun before() {
        today = "2020-01-28"
        tomorrow = "2020-01-29"
    }

    @Test
    fun getToday() {
        if (Build.VERSION.SDK_INT >= 26) {
            val date: String = LocalDate.now().toString()
            assertEquals(today, date)
        }
        val cal: Calendar = Calendar.getInstance()
        cal.time = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        val date: String = dateFormat.format(cal.time).toString()
        assertEquals(today, date)
    }

    @Test
    fun getTomorrow() {
        if (Build.VERSION.SDK_INT >= 26) {
            val date: String = LocalDate.now().plusDays(1).toString()
            assertEquals(tomorrow, date)
        }
        val cal: Calendar = Calendar.getInstance()
        cal.time = Date()
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        cal.add(Calendar.DATE, 1)

        val date: String = dateFormat.format(cal.time).toString()
        assertEquals(tomorrow, date)
    }

}