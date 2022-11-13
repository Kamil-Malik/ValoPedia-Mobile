package com.lelestacia.valorantgamepedia.data.model.remote.weapons_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkSkin(
    @SerializedName("assetPath")
    val assetPath: String,
    @SerializedName("chromas")
    val chromas: List<NetworkChroma>,
    @SerializedName("contentTierUuid")
    val contentTierUuid: String?,
    @SerializedName("displayIcon")
    val displayIcon: String?,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("levels")
    val networkLevels: List<NetworkLevel>,
    @SerializedName("themeUuid")
    val themeUuid: String,
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("wallpaper")
    val wallpaper: String?
) : Parcelable