package com.lelestacia.valorantgamepedia.data.model.remote.currencies_data


import com.google.gson.annotations.SerializedName

data class NetworkCurrencyData(
    @SerializedName("displayIcon")
    val displayIcon: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("displayNameSingular")
    val displayNameSingular: String,
    @SerializedName("largeIcon")
    val largeIcon: String,
    @SerializedName("uuid")
    val uuid: String
)