package com.lelestacia.valorantgamepedia.data.repository.contract

import com.lelestacia.valorantgamepedia.data.model.remote.news.NetworkNews

interface HendrikDevRepository {

    suspend fun getNews() : List<NetworkNews>
}