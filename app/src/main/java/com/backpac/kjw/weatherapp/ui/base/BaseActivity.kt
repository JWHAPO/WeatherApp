package com.backpac.kjw.weatherapp.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * WeatherApp
 * Class: BaseActivity
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Base Activity
 */
abstract class BaseActivity <T: ViewDataBinding, V: BaseViewModel> : AppCompatActivity(){

    private lateinit var viewDataBinding: T

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    abstract fun getBindingVariable(): Int

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        init()
    }

    private fun performDataBinding(){
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.setVariable(getBindingVariable(), getViewModel())
        viewDataBinding.executePendingBindings()
    }

    fun getViewDataBinding() : T {
        return this.viewDataBinding
    }

}