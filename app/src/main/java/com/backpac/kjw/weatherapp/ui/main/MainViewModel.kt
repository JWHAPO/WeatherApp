package com.backpac.kjw.weatherapp.ui.main

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.backpac.kjw.weatherapp.data.api.WeatherApi
import com.backpac.kjw.weatherapp.data.domain.weather.Weather
import com.backpac.kjw.weatherapp.extension.with
import com.backpac.kjw.weatherapp.repository.WeatherRepository
import com.backpac.kjw.weatherapp.ui.base.BaseViewModel
import com.backpac.kjw.weatherapp.util.DateUtil
import com.backpac.kjw.weatherapp.util.Event
import com.backpac.kjw.weatherapp.util.NotNullMutableLiveData
import io.reactivex.Observable

/**
 * WeatherApp
 * Class: MainViewModel
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Main ViewModel
 */
class MainViewModel(private val repo: WeatherRepository) : BaseViewModel() {
    private var isFinishFirstLoading: Boolean = false

    private val _navigateToDetails = MutableLiveData<Event<Weather>>()
    val navigateToDetails: MutableLiveData<Event<Weather>>
        get() = _navigateToDetails

    private val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: NotNullMutableLiveData<Boolean>
        get() = _refreshing

    private val _loading: NotNullMutableLiveData<Int> = NotNullMutableLiveData(8)
    val loading: NotNullMutableLiveData<Int>
        get() = _loading

    private val _items: NotNullMutableLiveData<List<Weather>> = NotNullMutableLiveData(arrayListOf())
    val items: NotNullMutableLiveData<List<Weather>>
        get() = _items

    private val _item: NotNullMutableLiveData<Weather> = NotNullMutableLiveData(Weather(arrayListOf(),"",0))
        val item: NotNullMutableLiveData<Weather>
        get() = _item

    fun getWeathers() {
        addToDisposable(
            repo.getLocations(BASIC_QUERY).with()
                .flatMap { location ->
                    Observable.fromIterable(location).flatMap { weather ->
                        repo.getWeather(weather.woeid).with()
                    }
                }
                .toSortedList { t, t2 -> t.title.compareTo(t2.title) }
                .doOnSubscribe {
                    _items.value = emptyList()
                    if (isFinishFirstLoading) _refreshing.value = true
                    else {
                        loading.value = 0
                        isFinishFirstLoading = true
                    }
                }
                .subscribe({
                    _items.value = it
                    disableProgressDialog()
                }, {
                    disableProgressDialog()
                })
        )
    }

    fun getWeather(woeid: Int){
        addToDisposable(
            repo.getWeather(woeid).with()
                .doOnSubscribe {

                }
                .subscribe({
                    item.value = it
                },{

                })
        )
    }

    private fun disableProgressDialog() {
        _refreshing.value = false
        _loading.value = 8
    }

    fun onRefreshListener(): SwipeRefreshLayout.OnRefreshListener =
        SwipeRefreshLayout.OnRefreshListener {
            getWeathers()
        }

    fun onClickListener(weather: Weather): View.OnClickListener =
        View.OnClickListener {
            _navigateToDetails.value = Event(weather)
        }

    companion object {
        //조회시 기본으로 사용되는 query 조건
        const val BASIC_QUERY = "se"
    }

}