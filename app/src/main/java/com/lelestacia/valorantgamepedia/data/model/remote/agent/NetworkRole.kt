package com.lelestacia.valorantgamepedia.data.model.remote.agent


import com.google.gson.annotations.SerializedName

data class NetworkRole(
    @SerializedName("description")
    val description: String,
    @SerializedName("displayIcon")
    val displayIcon: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("uuid")
    val uuid: String
)