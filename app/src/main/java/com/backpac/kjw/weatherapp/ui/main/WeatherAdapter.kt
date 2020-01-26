package com.backpac.kjw.weatherapp.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backpac.kjw.weatherapp.R
import com.backpac.kjw.weatherapp.data.domain.weather.Weather
import com.backpac.kjw.weatherapp.databinding.HeaderWeatherBinding
import com.backpac.kjw.weatherapp.databinding.ItemWeatherBinding
import com.backpac.kjw.weatherapp.ui.base.BaseViewHolder

/**
 * WeatherApp
 * Class: WeatherAdapter
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Weather list adapter
 */
class WeatherAdapter(var items: List<Weather> = arrayListOf(), val vm: MainViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val HEADER_TYPE: Int = 0
    private val ITEM_TYPE: Int = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER_TYPE) {
            WeatherHeaderAdapterViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.header_weather, parent, false)
            )
        } else {
            WeatherAdapterViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WeatherAdapterViewHolder) {
            holder.viewDataBinding.item = items[position]
            holder.viewDataBinding.today = items[position].consolidated_weather.filter { it.applicable_date.contains("2020-01-27",true) }[0]
            holder.viewDataBinding.tomorrow = items[position].consolidated_weather.filter { it.applicable_date.contains("2020-01-28",true) }[0]
            holder.viewDataBinding.vm = vm
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            HEADER_TYPE
        } else {
            ITEM_TYPE
        }
    }

    override fun getItemCount(): Int = items.size

    class WeatherAdapterViewHolder(view: View) : BaseViewHolder<ItemWeatherBinding>(view)
    class WeatherHeaderAdapterViewHolder(view: View) : BaseViewHolder<HeaderWeatherBinding>(view)
}