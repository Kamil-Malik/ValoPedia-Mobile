package com.lelestacia.valorantgamepedia.data.repository.implementation

import com.lelestacia.valorantgamepedia.data.data_source.api.HendrikDevApi
import com.lelestacia.valorantgamepedia.data.data_source.local.MemoryCache
import com.lelestacia.valorantgamepedia.data.model.remote.news.NetworkNews
import com.lelestacia.valorantgamepedia.data.repository.contract.HendrikDevRepository
import javax.inject.Inject

class HendrikDevRepositoryImpl @Inject constructor(
    private val apiService: HendrikDevApi
) : HendrikDevRepository {

    private val cache: MemoryCache = MemoryCache

    override suspend fun getNews(): List<NetworkNews> {
        return cache.news.ifEmpty {
            val response = apiService.getNews()
            cache.news.addAll(response.data)
            cache.news
        }
    }
}