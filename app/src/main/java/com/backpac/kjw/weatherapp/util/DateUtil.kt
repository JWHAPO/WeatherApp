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

    /**
     * 며칠 전, 며칠 후 일자 가져오기 형식은 yyyy-MM-dd
     * 파라미터에 0을 넣으면 오늘일자.
     */
    fun getDateByDays(diff: Int): String {
        return if (Build.VERSION.SDK_INT >= 26) {
            LocalDate.now().plusDays(1).toString()
        } else {
            val cal: Calendar = Calendar.getInstance()
            cal.time = Date()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd")

            cal.add(Calendar.DATE, diff)

            return dateFormat.format(cal.time).toString()
        }
    }

}