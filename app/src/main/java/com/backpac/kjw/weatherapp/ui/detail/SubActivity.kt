package com.backpac.kjw.weatherapp.ui.detail

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.backpac.kjw.weatherapp.BR
import com.backpac.kjw.weatherapp.R
import com.backpac.kjw.weatherapp.databinding.SubLayoutBinding
import com.backpac.kjw.weatherapp.ui.base.BaseActivity
import com.backpac.kjw.weatherapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * WeatherApp
 * Class: SubActivity
 * Created by JEONGWOOKIM on 2020-02-13.
 * Description:
 */

class SubActivity : BaseActivity<SubLayoutBinding, MainViewModel>() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.sub_layout

    override fun getViewModel(): MainViewModel = mainViewModel

    override fun getBindingVariable(): Int = BR.vm

    override fun init() {
        Toast.makeText(applicationContext, "${intent.extras.get("WOEID")} 입니다.", Toast.LENGTH_SHORT).show()

    }

    companion object {
        fun intentFor(context: Context, woeid: Int): Intent {
            val intent = Intent(context, SubActivity::class.java)
            intent.putExtra("WOEID", woeid)
            return intent
        }
    }
}