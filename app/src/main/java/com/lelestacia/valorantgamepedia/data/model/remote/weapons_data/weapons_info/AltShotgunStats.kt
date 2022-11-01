package com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.weapons_info


import com.google.gson.annotations.SerializedName

data class AltShotgunStats(
    @SerializedName("burstRate")
    val burstRate: Double,
    @SerializedName("shotgunPelletCount")
    val shotgunPelletCount: Int
)