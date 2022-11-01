package com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.weapons_info


import com.google.gson.annotations.SerializedName

data class AirBurstStats(
    @SerializedName("burstDistance")
    val burstDistance: Double,
    @SerializedName("shotgunPelletCount")
    val shotgunPelletCount: Int
)