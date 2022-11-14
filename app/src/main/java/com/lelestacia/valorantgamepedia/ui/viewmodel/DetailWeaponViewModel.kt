package com.lelestacia.valorantgamepedia.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.lelestacia.valorantgamepedia.data.model.local.weapon.relation.WeaponDataWithDamageRangeAndSkin
import com.lelestacia.valorantgamepedia.data.repository.contract.ValorantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailWeaponViewModel @Inject constructor(
    private val repository: ValorantRepository
) : ViewModel() {

    suspend fun getWeaponDetail(uuid: String) : WeaponDataWithDamageRangeAndSkin {
        return repository.getWeaponDetail(uuid)
    }
}