package com.lelestacia.valorantgamepedia.data.model.remote.weapon


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkAdsStatistic(
    @SerializedName("burstCount")
    val burstCount: Int,
    @SerializedName("fireRate")
    val fireRate: Double,
    @SerializedName("firstBulletAccuracy")
    val firstBulletAccuracy: Double,
    @SerializedName("runSpeedMultiplier")
    val runSpeedMultiplier: Double,
    @SerializedName("zoomMultiplier")
    val zoomMultiplier: Double
) : Parcelable