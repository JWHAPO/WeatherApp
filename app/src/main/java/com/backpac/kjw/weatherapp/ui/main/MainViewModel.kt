package com.backpac.kjw.weatherapp.ui.main

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.backpac.kjw.weatherapp.data.api.WeatherApi
import com.backpac.kjw.weatherapp.data.domain.weather.Weather
import com.backpac.kjw.weatherapp.extension.with
import com.backpac.kjw.weatherapp.ui.base.BaseViewModel
import com.backpac.kjw.weatherapp.util.NotNullMutableLiveData
import io.reactivex.Observable

/**
 * WeatherApp
 * Class: MainViewModel
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Main ViewModel
 */
class MainViewModel(private val api: WeatherApi) : BaseViewModel() {
    private var isFinishFirstLoading: Boolean = false
    private val weathers: MutableList<Weather> = mutableListOf()

    private val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: NotNullMutableLiveData<Boolean>
        get() = _refreshing

    private val _loading: NotNullMutableLiveData<Int> = NotNullMutableLiveData(8)
    val loading: NotNullMutableLiveData<Int>
        get() = _loading

    private val _items: NotNullMutableLiveData<List<Weather>> = NotNullMutableLiveData(arrayListOf())
    val items: NotNullMutableLiveData<List<Weather>>
        get() = _items

    fun getWeather() {

        addToDisposable(
            api.getLocations(BASIC_QUERY).with()
                .flatMap { itemList ->
                    Observable.fromIterable(itemList).flatMap { item ->
                        api.getWeathers(item.woeid).with()
                    }
                }
                .doOnSubscribe {
                    weathers.clear()
                    _items.value = weathers

                    if (isFinishFirstLoading) _refreshing.value = true
                    else{
                        loading.value = 0
                        isFinishFirstLoading = true
                    }
                }
                .doOnComplete {
                    disableProgressDialog()
                    _items.value = weathers
                }
                .subscribe({
                    weathers.add(it)
                }, {
                    disableProgressDialog()
                })
        )
    }

    private fun disableProgressDialog() {
        _refreshing.value = false
        _loading.value = 8
    }

    fun onRefreshListener(): SwipeRefreshLayout.OnRefreshListener =
        SwipeRefreshLayout.OnRefreshListener {
            getWeather()
        }

    companion object {
        //조회시 기본으로 사용되는 query 조건
        const val BASIC_QUERY = "se"
    }

}