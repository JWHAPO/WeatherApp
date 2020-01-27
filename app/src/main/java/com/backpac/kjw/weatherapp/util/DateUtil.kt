package com.backpac.kjw.weatherapp.util

import android.os.Build
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*


/**
 * WeatherApp
 * Class: WeatherAdapter
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Weather list adapter
 */
object DateUtil {

    fun getToday(): String {
        return if (Build.VERSION.SDK_INT >= 26) {
            LocalDate.now().toString()
        } else {
            val cal: Calendar = Calendar.getInstance()
            cal.time = Date()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")

            dateFormat.format(cal.time).toString()
        }
    }

    fun getTomorrow(): String {
        return if (Build.VERSION.SDK_INT >= 26) {
            LocalDate.now().plusDays(1).toString()
        } else {
            val cal: Calendar = Calendar.getInstance()
            cal.time = Date()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")

            cal.add(Calendar.DATE, 1)

            return dateFormat.format(cal.time).toString()
        }
    }

}