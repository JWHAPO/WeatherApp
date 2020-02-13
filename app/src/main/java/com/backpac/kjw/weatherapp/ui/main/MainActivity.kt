package com.backpac.kjw.weatherapp.ui.main

import android.widget.Toast
import androidx.lifecycle.Observer
import com.backpac.kjw.weatherapp.BR
import com.backpac.kjw.weatherapp.R
import com.backpac.kjw.weatherapp.databinding.MainLayoutBinding
import com.backpac.kjw.weatherapp.ui.base.BaseActivity
import com.backpac.kjw.weatherapp.ui.detail.SubActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * WeatherApp
 * Class: MainActivity
 * Created by JEONGWOOKIM on 2020-01-22.
 * Description: Display today and tomorrow's weather of searched locations.
 */
class MainActivity : BaseActivity<MainLayoutBinding, MainViewModel>() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.main_layout

    override fun getBindingVariable(): Int  = BR.vm

    override fun getViewModel(): MainViewModel = mainViewModel

    override fun init() {
        mainViewModel.getWeather()

        mainViewModel.navigateToDetails.observe(this,
            Observer { it ->
                it.getContentIfNotHandled()?.let {
                    val intent = SubActivity.intentFor(this, it.woeid)
                    startActivity(intent)
                }
            })
    }
}