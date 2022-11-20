package com.lelestacia.valorantgamepedia.data.repository.implementation

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lelestacia.valorantgamepedia.data.data_source.api.HendrikDevApi
import com.lelestacia.valorantgamepedia.data.data_source.local.NewsDatabase
import com.lelestacia.valorantgamepedia.data.model.local.news.entity.News
import com.lelestacia.valorantgamepedia.data.model.mapper.MapToLocal
import com.lelestacia.valorantgamepedia.data.repository.contract.HendrikDevRepository
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class HendrikDevRepositoryImpl @Inject constructor(
    private val apiService: HendrikDevApi,
    database: NewsDatabase,
    private val ioDispatcher: CoroutineContext
) : HendrikDevRepository {

    private val newsDao = database.newsDao()
    private val isUpdated = false

    override fun getPagedNews(): Flow<PagingData<News>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            )
        ) {
            newsDao.getPagingNews()
        }.flow
    }

    override fun pagedNews(): Flow<FinalResponse<Flow<PagingData<News>>>> =
        flow<FinalResponse<Flow<PagingData<News>>>> {
            if (newsDao.getAllNews().isNotEmpty()) {
                emit(
                    FinalResponse.Success(
                        Pager(
                            config = PagingConfig(
                                pageSize = 5
                            )
                        ) {
                            newsDao.getPagingNews()
                        }.flow
                    )
                )
            }

            if (isUpdated) return@flow

            val apiResponse = apiService.getNews().data.map {
                MapToLocal().news(it)
            }
            newsDao.insertListOfNews(apiResponse)
        }.onStart { emit(FinalResponse.Loading) }.catch { e ->
            if (newsDao.getAllNews().isNotEmpty()) {
                emit(
                    FinalResponse.Success(
                        Pager(config = PagingConfig(pageSize = 5)) {
                            newsDao.getPagingNews()
                        }.flow
                    )
                )
            }

            when (e) {
                is IOException -> emit(FinalResponse.IoException)
                is HttpException -> emit(FinalResponse.HttpException(e.code(), e.message()))
                else -> emit(FinalResponse.GenericException(e.message))
            }
        }.flowOn(ioDispatcher)
}