package com.lelestacia.valorantgamepedia.data.data_source.local

import com.lelestacia.valorantgamepedia.data.model.remote.news.NetworkNews

object MemoryCache {

    val news = arrayListOf<NetworkNews>()
}