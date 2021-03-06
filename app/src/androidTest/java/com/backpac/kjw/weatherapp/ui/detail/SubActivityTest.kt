package com.backpac.kjw.weatherapp.ui.detail

import androidx.test.filters.SdkSuppress
import androidx.test.filters.SmallTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * WeatherApp
 * Class: SubActivityTest
 * Created by JEONGWOOKIM on 2020-02-25.
 * Description:
 */

const val BASIC_PACKAGE = "com.backpac.hapo.weatherapp"

@RunWith(AndroidJUnit4ClassRunner::class)
@SdkSuppress(minSdkVersion = 18)
@SmallTest
class SubActivityTest{
    private lateinit var mDevice: UiDevice

    @Before
    fun setUp(){
        mDevice = UiDevice.getInstance(getInstrumentation())
    }

    @Test
    fun checkPreconditions(){
        assertThat(mDevice, notNullValue())
    }

}