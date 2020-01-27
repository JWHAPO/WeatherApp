package com.backpac.kjw.weatherapp

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.backpac.kjw.weatherapp.data.api.WeatherApi
import com.backpac.kjw.weatherapp.di.apiModule
import com.backpac.kjw.weatherapp.di.networkModule
import com.backpac.kjw.weatherapp.di.viewModelModule
import com.backpac.kjw.weatherapp.extension.with
import com.backpac.kjw.weatherapp.ui.main.MainViewModel
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.notNullValue
import org.junit.BeforeClass
import org.koin.test.inject

class SimpleTest : KoinTest {
    private lateinit var viewModel: MainViewModel
    private val api: WeatherApi by inject()

    companion object{
        @BeforeClass
        @JvmStatic
        fun setupClass(){
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        }
    }

    @Before
    fun before(){
        startKoin {
            modules(
                listOf(
                    networkModule,
                    apiModule,
                    viewModelModule
                )
            )
        }

        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor(){
            override fun executeOnDiskIO(runnable: Runnable) = runnable.run()
            override fun isMainThread(): Boolean = true
            override fun postToMainThread(runnable: Runnable) = runnable.run()
        })

        viewModel = MainViewModel(get())
    }

    @After
    fun after(){
        ArchTaskExecutor.getInstance().setDelegate(null)
        stopKoin()
    }

    @Test
    fun viewModelNotNull(){
        assertThat(viewModel, notNullValue())
    }

    @Test
    fun apiTest() {
        print("------API TEST START-------")

        api.getLocations("se")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {  }
            .doOnError {  }
            .subscribe({
                println(it.size)
            },{
                println(it.toString())
            })

    }
}