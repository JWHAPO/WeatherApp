package com.backpac.kjw.weatherapp.ui.main

import android.util.Log
import android.view.View
import androidx.core.view.get
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.backpac.kjw.weatherapp.data.domain.weather.Weather
import com.backpac.kjw.weatherapp.repository.WeatherRepository
import com.backpac.kjw.weatherapp.ui.base.BaseViewModel
import com.backpac.kjw.weatherapp.util.Event
import com.backpac.kjw.weatherapp.util.NotNullMutableLiveData
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 * WeatherApp
 * Class: MainViewModel
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Main ViewModel
 */
class MainViewModel(private val weatherRepository: WeatherRepository) : BaseViewModel() {
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
            weatherRepository.getWeathers(BASIC_QUERY)
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
            weatherRepository.getWeather(woeid)
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
    fun onScrollListener() : RecyclerView.OnScrollListener {
        return object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d("TAG","onScrollStateChanged: ")
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                Log.d("TAG","onScrolled: ")

                val linearLayoutManager: LinearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
                if(!_refreshing.value){
                    if(linearLayoutManager.findLastCompletelyVisibleItemPosition() == _items.value.size  ){
                        _refreshing.value = true
                        Log.d("TAG","findLastCompletelyVisibleItemPosition: ")
                    }
                }

            }
        }
    }

    companion object {
        //조회시 기본으로 사용되는 query 조건
        const val BASIC_QUERY = "se"
    }

}