package com.lelestacia.valorantgamepedia.data.repository.contract

import androidx.paging.PagingData
import com.lelestacia.valorantgamepedia.data.model.local.news.entity.News
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import kotlinx.coroutines.flow.Flow

interface HendrikDevRepository {

    fun getPagedNews() : Flow<PagingData<News>>

    fun pagedNews(): Flow<FinalResponse<Flow<PagingData<News>>>>

}