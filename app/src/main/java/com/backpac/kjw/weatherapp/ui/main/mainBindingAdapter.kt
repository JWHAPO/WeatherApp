package com.backpac.kjw.weatherapp.ui.main

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.backpac.kjw.weatherapp.constant.Constants
import com.backpac.kjw.weatherapp.data.domain.weather.Weather
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import java.util.*
import kotlin.math.roundToInt

/**
 * WeatherApp
 * Class: mainBindingAdapter
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Main Adapter Binding
 */
@BindingAdapter(value = ["items", "viewModel"])
fun setWeathers(view: RecyclerView, items: List<Weather>, vm: MainViewModel) {
    view.adapter?.run {
        if (this is WeatherAdapter) {
            if (items.isNotEmpty()) {
                val headerItem = Weather(arrayListOf(),"",0)
                val weatherItems = LinkedList(items)
                weatherItems.push(headerItem)
                this.items = weatherItems
                this.notifyDataSetChanged()
            } else {
                this.items = items
                this.notifyDataSetChanged()
            }
        }
    } ?: run {
        WeatherAdapter(items, vm).apply {
            view.adapter = this
        }
    }
}

@BindingAdapter("convertTemp")
fun convertTemp(view: TextView, value: Double) {
    view.text = value.roundToInt().toString()
}

@BindingAdapter("weatherIcon")
fun setWeatherIcon(view: ImageView, stateAbbr: String) {
    val iconUrl: String = Constants.WEATHER_ICON_PATH.replace("X", stateAbbr)
    Glide.with(view.context)
        .load(iconUrl)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .into(view)
}

@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refreshing(visible: Boolean) {
    isRefreshing = visible
}