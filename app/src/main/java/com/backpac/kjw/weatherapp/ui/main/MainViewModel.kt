package com.backpac.kjw.weatherapp.ui.main

import android.util.Log
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.backpac.kjw.weatherapp.constant.Constants
import com.backpac.kjw.weatherapp.data.api.WeatherApi
import com.backpac.kjw.weatherapp.data.domain.Location
import com.backpac.kjw.weatherapp.data.domain.weather.Weather
import com.backpac.kjw.weatherapp.extension.with
import com.backpac.kjw.weatherapp.ui.base.BaseViewModel
import com.backpac.kjw.weatherapp.util.NotNullMutableLiveData
import io.reactivex.Observable
import io.reactivex.Single

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

    private val _items: NotNullMutableLiveData<List<Weather>> =
        NotNullMutableLiveData(arrayListOf())
    val items: NotNullMutableLiveData<List<Weather>>
        get() = _items

    fun getWeather() {

        addToDisposable(
            api.getLocations(query).with()
                .flatMap { itemList ->
                    Observable.fromIterable(itemList).flatMap { item ->
                        api.getWeathers(item.woeid).with()
                    }
                }
                .toList()
                .doOnSubscribe {
                    _items.value = emptyList()
                    if (this.isFinishFirstLoading) _refreshing.value = true
                    else _loading.value = 0
                }
                .doOnSuccess {
                    if (this.isFinishFirstLoading) _refreshing.value = false
                    else _loading.value = 8

                    this.isFinishFirstLoading = true
                }
                .subscribe(
                    {
//                        for( a in it){
//                            for( b in a.consolidated_weather){
//                                b.image_url = "${Constants.WEATHER_ICON_PATH}".replace("X",b.weather_state_abbr)
//                            }
//                        }

                        _items.value = it
                    }, {
                        if (this.isFinishFirstLoading) _refreshing.value = false
                        else _loading.value = 8
                    }
                )
        )
    }

    fun onRefreshListener(): SwipeRefreshLayout.OnRefreshListener =
        SwipeRefreshLayout.OnRefreshListener {
            getWeather()
        }

}