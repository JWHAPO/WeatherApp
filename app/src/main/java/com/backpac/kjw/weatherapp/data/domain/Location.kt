package com.backpac.kjw.weatherapp.data.domain

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/**
 * WeatherApp
 * Class: Location
 * Created by JEONGWOOKIM on 2020-01-22.
 * Description: Location Data Class
 */
data class Location(
    @SerializedName("title") val title: String,
    @SerializedName("woeid") val woeid: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.run {
            writeString(title)
            writeInt(woeid)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }

}
