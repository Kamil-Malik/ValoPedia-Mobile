package com.lelestacia.valorantgamepedia.data.repository.contract

import androidx.paging.PagingData
import com.lelestacia.valorantgamepedia.data.model.local.news.entity.News
import kotlinx.coroutines.flow.Flow

interface HendrikDevRepository {

    suspend fun updateNews(): Boolean

    fun getPagedNews() : Flow<PagingData<News>>
}