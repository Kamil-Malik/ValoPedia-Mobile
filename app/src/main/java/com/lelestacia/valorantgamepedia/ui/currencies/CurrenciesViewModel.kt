package com.lelestacia.valorantgamepedia.ui.currencies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.CurrenciesData
import com.lelestacia.valorantgamepedia.data.repository.MainRepository
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {

    fun getCurrencies(): LiveData<FinalResponse<List<CurrenciesData>>> {
        return repository.getCurrencies().asLiveData()
    }
}