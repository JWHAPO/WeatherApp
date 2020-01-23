package com.backpac.kjw.weatherapp.extension

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * WeatherApp
 * Class: AdapterExtension
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Adapter Extension
 */

@BindingAdapter("refreshing")
fun SwipeRefreshLayout.refreshing(visible: Boolean) {
    isRefreshing = visible
}