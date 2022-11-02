package com.lelestacia.valorantgamepedia.data.model.remote.maps_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Callout(
    @SerializedName("location")
    val location: Location,
    @SerializedName("regionName")
    val regionName: String,
    @SerializedName("superRegionName")
    val superRegionName: String
): Parcelable