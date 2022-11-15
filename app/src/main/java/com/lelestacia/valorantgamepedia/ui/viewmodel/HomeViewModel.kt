package com.lelestacia.valorantgamepedia.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.lelestacia.valorantgamepedia.data.model.local.news.entity.News
import com.lelestacia.valorantgamepedia.data.repository.contract.HendrikDevRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HendrikDevRepository
) : ViewModel() {

    private val _isValidated = MutableLiveData<Boolean>()
    val isValidated get() = _isValidated as LiveData<Boolean>

    init {
        viewModelScope.launch {
            _isValidated.value = repository.updateNews()
        }
    }

    fun getPagedNews() : Flow<PagingData<News>> {
        return repository.getPagedNews().cachedIn(viewModelScope)
    }
}