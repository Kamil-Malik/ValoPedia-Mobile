package com.lelestacia.valorantgamepedia.data.model.remote.weapons_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkChroma(
    @SerializedName("assetPath")
    val assetPath: String,
    @SerializedName("displayIcon")
    val displayIcon: String?,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("fullRender")
    val fullRender: String,
    @SerializedName("streamedVideo")
    val streamedVideo: String?,
    @SerializedName("swatch")
    val swatch: String?,
    @SerializedName("uuid")
    val uuid: String
) : Parcelable