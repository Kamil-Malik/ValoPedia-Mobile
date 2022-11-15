package com.lelestacia.valorantgamepedia.data.model.remote.news

import com.google.gson.annotations.SerializedName

data class NetworkNews(

    @SerializedName("banner_url")
    val bannerUrl: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("external_link")
    val externalLink: String?,

    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val url: String
)