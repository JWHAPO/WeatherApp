package com.backpac.kjw.weatherapp.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * WeatherApp
 * Class: BaseViewModel
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Base ViewModel
 */
open class BaseViewModel : ViewModel(){
    private val disposables: CompositeDisposable = CompositeDisposable()

    fun addToDisposable(disposable: Disposable){
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}