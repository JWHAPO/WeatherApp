package com.backpac.kjw.weatherapp.ui.base

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * WeatherApp
 * Class: BaseViewHolder
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Base ViewHolder
 */
abstract class BaseViewHolder<out T : ViewDataBinding>(view: View) : RecyclerView.ViewHolder(view) {
    val viewDataBinding: T = DataBindingUtil.bind(view)!!
}