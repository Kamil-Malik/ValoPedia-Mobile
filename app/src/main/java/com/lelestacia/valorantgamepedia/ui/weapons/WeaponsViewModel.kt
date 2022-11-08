package com.lelestacia.valorantgamepedia.ui.weapons

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeaponData
import com.lelestacia.valorantgamepedia.data.repository.MainRepository
import com.lelestacia.valorantgamepedia.data.repository.SharedPrefRepository
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeaponsViewModel @Inject constructor(
    private val repository: MainRepository,
    private val sharedPrefRepository: SharedPrefRepository
) : ViewModel() {

    fun getWeapons() : LiveData<FinalResponse<List<NetworkWeaponData>>> {
        return repository.getWeapons().asLiveData()
    }

    fun getListType() : Boolean = sharedPrefRepository.getLisType()

    fun setListType(isList: Boolean) = sharedPrefRepository.setListType(isList)
}