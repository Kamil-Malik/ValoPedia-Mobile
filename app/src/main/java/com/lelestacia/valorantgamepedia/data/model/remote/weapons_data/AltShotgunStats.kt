package com.lelestacia.valorantgamepedia.data.model.remote.weapons_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AltShotgunStats(
    @SerializedName("burstRate")
    val burstRate: Double,
    @SerializedName("shotgunPelletCount")
    val shotgunPelletCount: Int
): Parcelable