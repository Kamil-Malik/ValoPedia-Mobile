package com.lelestacia.valorantgamepedia.data.repository.implementation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lelestacia.valorantgamepedia.data.data_source.api.HendrikDevApi
import com.lelestacia.valorantgamepedia.data.data_source.local.LocalDatabase
import com.lelestacia.valorantgamepedia.data.model.local.news.entity.News
import com.lelestacia.valorantgamepedia.data.model.mapper.MapToLocal
import com.lelestacia.valorantgamepedia.data.repository.contract.HendrikDevRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class HendrikDevRepositoryImpl @Inject constructor(
    private val apiService: HendrikDevApi,
    localDatabase: LocalDatabase,
    private val ioDispatcher: CoroutineContext
) : HendrikDevRepository {

    private val newsDao = localDatabase.newsDao()

    override suspend fun updateNews(): Boolean {
        return withContext(ioDispatcher) {
            try {
                val apiResponse = apiService
                    .getNews().data.map { MapToLocal().news(it) }
                newsDao.insertListOfNews(apiResponse)
                true
            } catch (e: Exception) {
                false
            }
        }
    }

    override fun getPagedNews(): Flow<PagingData<News>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            newsDao.getAllStory()
        }.flow
    }
}