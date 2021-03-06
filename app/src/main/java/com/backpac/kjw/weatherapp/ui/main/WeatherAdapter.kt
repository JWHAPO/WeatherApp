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
import com.backpac.kjw.weatherapp.util.DateUtil

/**
 * WeatherApp
 * Class: WeatherAdapter
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Weather list adapter
 */
class WeatherAdapter(var items: List<Weather>, val vm: MainViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HEADER_TYPE) {
            WeatherHeaderAdapterViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.header_weather, parent, false)
            )
        } else if (viewType == LOADING_TYPE) {
            WeatherLoadingAdapterViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            )
        }else  {
            WeatherAdapterViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WeatherAdapterViewHolder) {
            holder.viewDataBinding.item = items[position]
            holder.viewDataBinding.today = items[position].consolidated_weather.find { it.applicable_date.contains(DateUtil.getDateByDays(0),true) }
            holder.viewDataBinding.tomorrow = items[position].consolidated_weather.find { it.applicable_date.contains(DateUtil.getDateByDays(1),true) }
            holder.viewDataBinding.vm = vm
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            HEADER_TYPE
        } else {
            if(items[position] == null) LOADING_TYPE else ITEM_TYPE
        }
    }

    override fun getItemCount(): Int = items.size

    class WeatherAdapterViewHolder(view: View) : BaseViewHolder<ItemWeatherBinding>(view)
    class WeatherHeaderAdapterViewHolder(view: View) : BaseViewHolder<HeaderWeatherBinding>(view)
    class WeatherLoadingAdapterViewHolder(view: View) : BaseViewHolder<ItemWeatherBinding>(view)

    companion object{
        // header's view type of weather list
        const val HEADER_TYPE: Int = 0
        // item's view type of weather list
        const val ITEM_TYPE: Int = 1
        // item's view type of loading
        const val LOADING_TYPE: Int = 2
    }
}