package com.backpac.kjw.weatherapp.ui.custom

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.backpac.kjw.weatherapp.R

/**
 * WeatherApp
 * Class: SImpleWeatherView
 * Created by JEONGWOOKIM on 2020-02-14.
 * Description:
 */

class SimpleWeatherView constructor(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private lateinit var iconImageView: ImageView
    private lateinit var titleTextView: AppCompatTextView

    init {
        initView()
        getAttrs(attrs)
    }

    private fun initView() {
        val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.simple_weather, this, false)
        addView(view)

        iconImageView = findViewById(R.id.simple_weather_icon_image_view)
        titleTextView = findViewById(R.id.simple_weather_state_text_view)
    }

    private fun getAttrs(attrs: AttributeSet) {
        val typedArray: TypedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SimpleWeatherView, 0, 0
        )
        setTypedArray(typedArray)
    }

    private fun setTypedArray(typedArray: TypedArray) {
        val weatherImageViewResId = typedArray.getResourceId(R.styleable.SimpleWeatherView_image, 0)
        val titleTextViewResId = typedArray.getString(R.styleable.SimpleWeatherView_title)

        iconImageView.setImageResource(weatherImageViewResId)
        titleTextView.text = titleTextViewResId
        typedArray.recycle()
    }

    fun setImage(imageResId: Int){
        iconImageView.setBackgroundResource(imageResId)
    }

    fun setTitle(title: String){
        titleTextView.text = title
    }

}