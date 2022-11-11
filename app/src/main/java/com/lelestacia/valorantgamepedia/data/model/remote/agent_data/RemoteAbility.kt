package com.lelestacia.valorantgamepedia.data.model.remote.agent_data


import com.google.gson.annotations.SerializedName

data class RemoteAbility(
    @SerializedName("description")
    val description: String,
    @SerializedName("displayIcon")
    val displayIcon: String?,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("slot")
    val slot: String
)