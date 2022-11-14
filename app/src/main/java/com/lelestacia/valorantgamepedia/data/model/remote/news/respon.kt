package com.lelestacia.valorantgamepedia.data.model.remote.news


import com.google.gson.annotations.SerializedName

data class respon(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: Int
)