package com.backpac.kjw.weatherapp.ui.main

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.backpac.kjw.weatherapp.di.apiModule
import com.backpac.kjw.weatherapp.di.networkModule
import com.backpac.kjw.weatherapp.di.viewModelModule
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get

/**
 * WeatherApp
 * Class: MainViewModelTest
 * Created by JEONGWOOKIM on 2020-01-28.
 * Description:
 */
class MainViewModelTest : KoinTest {
    private lateinit var viewModel: MainViewModel

    @Before
    fun before() {
        startKoin {
            modules(
                listOf(
                    networkModule,
                    apiModule,
                    viewModelModule
                )
            )
        }

        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
            override fun executeOnDiskIO(runnable: Runnable) = runnable.run()
            override fun isMainThread(): Boolean = true
            override fun postToMainThread(runnable: Runnable) = runnable.run()
        })

        viewModel = MainViewModel(get())

    }

    @After
    fun after() {
        ArchTaskExecutor.getInstance().setDelegate(null)
        stopKoin()
    }

    @Test
    fun viewModelIsNotNull() {
        assertThat(viewModel, Matchers.notNullValue())
    }
}