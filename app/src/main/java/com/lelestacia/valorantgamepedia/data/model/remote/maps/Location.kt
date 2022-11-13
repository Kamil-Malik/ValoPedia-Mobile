package com.lelestacia.valorantgamepedia.data.model.remote.maps


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(
    @SerializedName("x")
    val x: Double,
    @SerializedName("y")
    val y: Double
): Parcelable