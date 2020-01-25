package com.backpac.kjw.weatherapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backpac.kjw.weatherapp.R
import com.backpac.kjw.weatherapp.data.domain.weather.Weather
import com.backpac.kjw.weatherapp.databinding.ItemWeatherBinding
import com.backpac.kjw.weatherapp.ui.base.BaseViewHolder

/**
 * WeatherApp
 * Class: WeatherAdapter
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Weather list adapter
 */
class WeatherAdapter(var items: List<Weather> = arrayListOf(), val vm: MainViewModel) :
    RecyclerView.Adapter<WeatherAdapter.WeatherAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherAdapterViewHolder {
        return WeatherAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherAdapterViewHolder, position: Int) {
        holder.viewDataBinding.item = items[position]
        holder.viewDataBinding.vm = vm
    }

    override fun getItemCount(): Int = items.size

    class WeatherAdapterViewHolder(view: View) : BaseViewHolder<ItemWeatherBinding>(view)
}