package com.lelestacia.valorantgamepedia.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.lelestacia.valorantgamepedia.data.model.local.news.entity.News
import com.lelestacia.valorantgamepedia.data.repository.contract.HendrikDevRepository
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HendrikDevRepository
) : ViewModel() {

    fun getPagedNews(): LiveData<FinalResponse<Flow<PagingData<News>>>> {
        return repository.pagedNews().flowOn(Dispatchers.Main).asLiveData()
    }
}