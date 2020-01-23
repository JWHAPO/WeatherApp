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
    private var isFinishFirstLoading: Boolean = false

    private val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: NotNullMutableLiveData<Boolean>
        get() = _refreshing

    private val _loading: NotNullMutableLiveData<Int> = NotNullMutableLiveData(8)
    val loading: NotNullMutableLiveData<Int>
        get() = _loading

    private val _items: NotNullMutableLiveData<List<Location>> = NotNullMutableLiveData(arrayListOf())
    val items: NotNullMutableLiveData<List<Location>>
        get() = _items

    fun getWeather() {
        addToDisposable(
            api.getLocations(query).with()
                .doOnSubscribe {
                    _items.value = emptyList()
                    if(this.isFinishFirstLoading)_refreshing.value = true
                    else _loading.value = 0
                }
                .doOnNext {
                    if(this.isFinishFirstLoading)_refreshing.value = true
                    else _loading.value = 0
                }
                .doOnError {
                    if(this.isFinishFirstLoading)_refreshing.value = false
                    else _loading.value = 8
                }
                .doOnComplete {
                    if(this.isFinishFirstLoading)_refreshing.value = false
                    else _loading.value = 8

                    this.isFinishFirstLoading = true
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