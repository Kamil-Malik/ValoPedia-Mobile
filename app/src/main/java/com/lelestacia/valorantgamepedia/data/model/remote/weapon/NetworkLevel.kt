package com.lelestacia.valorantgamepedia.data.model.remote.weapon


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkLevel(
    @SerializedName("assetPath")
    val assetPath: String,
    @SerializedName("displayIcon")
    val displayIcon: String?,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("levelItem")
    val levelItem: String?,
    @SerializedName("streamedVideo")
    val streamedVideo: String?,
    @SerializedName("uuid")
    val uuid: String
) : Parcelable