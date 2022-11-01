package com.lelestacia.valorantgamepedia.data.model.remote.agent_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Media(
    @SerializedName("id")
    val id: Int,
    @SerializedName("wave")
    val wave: String,
    @SerializedName("wwise")
    val wwise: String
) : Parcelable