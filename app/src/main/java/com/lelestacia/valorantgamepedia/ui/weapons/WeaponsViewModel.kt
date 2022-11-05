package com.lelestacia.valorantgamepedia.ui.weapons

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.WeaponsData
import com.lelestacia.valorantgamepedia.data.repository.MainRepository
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeaponsViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    fun getWeapons() : LiveData<FinalResponse<List<WeaponsData>>> {
        return repository.getWeapons().asLiveData()
    }
}