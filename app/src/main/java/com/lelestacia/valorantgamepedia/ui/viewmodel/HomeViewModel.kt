package com.lelestacia.valorantgamepedia.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lelestacia.valorantgamepedia.data.model.remote.news.NetworkNews
import com.lelestacia.valorantgamepedia.data.repository.contract.HendrikDevRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HendrikDevRepository
) : ViewModel() {

    val news = MutableLiveData<List<NetworkNews>>()

    init {
        getNews()
    }

    fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            news.postValue(repository.getNews())
        }
    }
}