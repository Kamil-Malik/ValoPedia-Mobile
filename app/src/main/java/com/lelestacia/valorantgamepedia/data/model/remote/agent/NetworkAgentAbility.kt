package com.lelestacia.valorantgamepedia.data.model.remote.agent


import com.google.gson.annotations.SerializedName

data class NetworkAgentAbility(
    @SerializedName("description")
    val description: String,
    @SerializedName("displayIcon")
    val displayIcon: String?,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("slot")
    val slot: String
)