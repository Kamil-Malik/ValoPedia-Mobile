package com.lelestacia.valorantgamepedia.data.model.remote.agent_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VoiceLine(
    @SerializedName("maxDuration")
    val maxDuration: Double,
    @SerializedName("mediaList")
    val mediaList: List<Media>,
    @SerializedName("minDuration")
    val minDuration: Double
) : Parcelable