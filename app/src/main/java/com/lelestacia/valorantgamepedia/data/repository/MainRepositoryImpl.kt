package com.lelestacia.valorantgamepedia.data.repository

import com.lelestacia.valorantgamepedia.data.api.ValorantApi
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.AgentData
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.CurrenciesData
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.weapons_info.WeaponsData
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ValorantApi, private val coroutineDispatcher: CoroutineDispatcher
) : MainRepository {

    override fun getAgents(): Flow<FinalResponse<List<AgentData>>> {
        return flow<FinalResponse<List<AgentData>>> {
            emit(FinalResponse.Success(apiService.getAgents().data
                .sortedBy { it.displayName }
                .filter { it.isPlayableCharacter }))
        }.flowOn(coroutineDispatcher)
            .onStart {
                emit(FinalResponse.Loading)
            }.catch {
                when (this) {
                    is IOException -> emit(FinalResponse.IoException)
                    is HttpException -> emit(FinalResponse.GenericException(code(), message()))
                    else -> emit(FinalResponse.GenericException(null, it.message))
                }
            }
    }

    override fun getCurrencies(): Flow<FinalResponse<List<CurrenciesData>>> {
        return flow<FinalResponse<List<CurrenciesData>>> {
            emit(FinalResponse.Success(apiService.getCurrencies().data))
        }.flowOn(coroutineDispatcher)
            .onStart { emit(FinalResponse.Loading) }
            .catch {
                when (this) {
                    is IOException -> emit(FinalResponse.IoException)
                    is HttpException -> emit(FinalResponse.GenericException(code(), message()))
                    else -> emit(FinalResponse.GenericException(null, it.message))
                }
            }
    }

    override fun getWeapons(): Flow<FinalResponse<List<WeaponsData>>> {
        return flow<FinalResponse<List<WeaponsData>>> {
            emit(FinalResponse.Success(apiService.getWeapons().data.sortedBy { it.displayName }))
        }.flowOn(coroutineDispatcher)
            .onStart { emit(FinalResponse.Loading) }
            .catch {
                when (this) {
                    is IOException -> emit(FinalResponse.IoException)
                    is HttpException -> emit(FinalResponse.GenericException(code(), message()))
                    else -> emit(FinalResponse.GenericException(null, it.message))
                }
            }
    }
}