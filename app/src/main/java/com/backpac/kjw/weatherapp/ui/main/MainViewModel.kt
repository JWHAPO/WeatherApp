package com.backpac.kjw.weatherapp.ui.main

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.backpac.kjw.weatherapp.data.api.WeatherApi
import com.backpac.kjw.weatherapp.data.domain.Location
import com.backpac.kjw.weatherapp.extension.with
import com.backpac.kjw.weatherapp.ui.base.BaseViewModel
import com.backpac.kjw.weatherapp.util.NotNullMutableLiveData

/**
 * WeatherApp
 * Class: MainViewModel
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Main ViewModel
 */
class MainViewModel(private val api: WeatherApi) : BaseViewModel() {
    private val query: String = "se"

    private val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: NotNullMutableLiveData<Boolean>
        get() = _refreshing

    private val _items: NotNullMutableLiveData<List<Location>> = NotNullMutableLiveData(arrayListOf())
    val items: NotNullMutableLiveData<List<Location>>
        get() = _items

    fun getWeather() {
        addToDisposable(
            api.getLocations(query).with()
                .doOnSubscribe {
                    _refreshing.value = true
                }
                .doOnNext {
                    _refreshing.value = true
                }
                .doOnError {
                    _refreshing.value = false
                }
                .doOnComplete {
                    _refreshing.value = false
                }
                .subscribe({
                    _items.value = it
                }, {
                    //error
                })
        )
    }

    fun onRefreshListener(): SwipeRefreshLayout.OnRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        getWeather()
    }

}