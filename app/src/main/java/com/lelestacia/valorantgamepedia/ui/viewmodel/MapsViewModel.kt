package com.lelestacia.valorantgamepedia.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lelestacia.valorantgamepedia.data.model.local.maps.entity.Map
import com.lelestacia.valorantgamepedia.data.repository.contract.ValorantRepository
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repository: ValorantRepository
) : ViewModel() {

    fun getMaps() : LiveData<FinalResponse<List<Map>>> {
        return repository.getMaps().asLiveData()
    }
}