package com.lelestacia.valorantgamepedia.ui.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.LocalMapData
import com.lelestacia.valorantgamepedia.data.repository.MainRepository
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    fun getMaps() : LiveData<FinalResponse<List<LocalMapData>>> {
        return repository.getMaps().asLiveData()
    }
}