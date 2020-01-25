package com.backpac.kjw.weatherapp.ui.main

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.backpac.kjw.weatherapp.data.domain.weather.Weather

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
            this.items = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        WeatherAdapter(items, vm).apply {
            view.adapter = this
        }
    }
}