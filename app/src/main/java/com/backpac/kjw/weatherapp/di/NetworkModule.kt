package com.backpac.kjw.weatherapp.di

import com.backpac.kjw.weatherapp.BuildConfig
import com.backpac.kjw.weatherapp.constant.Constants
import com.backpac.kjw.weatherapp.data.api.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * WeatherApp
 * Class: NetworkModule
 * Created by JEONGWOOKIM on 2020-01-23.
 * Description: Network module
 */

private const val CONNECT_TIMEOUT: Long = 15L
private const val WRITE_TIMEOUT: Long = 15L
private const val READ_TIMEOUT: Long = 15L

val networkModule = module {

    single {
        okHttp()
    }

    single {
        retrofit()
    }

    single {
        get<Retrofit>().create(WeatherApi::class.java)
    }

}

private fun okHttp() = OkHttpClient.Builder().apply {
    connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
    writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    addInterceptor(
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
    )
}.build()

private fun retrofit() = Retrofit.Builder().apply {
    baseUrl(Constants.BASE_URL)
    addConverterFactory(GsonConverterFactory.create())
    addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    client(okHttp())
}.build()

