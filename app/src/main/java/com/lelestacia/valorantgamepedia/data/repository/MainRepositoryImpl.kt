package com.lelestacia.valorantgamepedia.data.repository

import com.lelestacia.valorantgamepedia.data.api.ValorantApi
import com.lelestacia.valorantgamepedia.data.local.MemoryCache
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.AgentData
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.CurrenciesData
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.MapsData
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

    private val memoryCache = MemoryCache

    override fun getAgents(): Flow<FinalResponse<List<AgentData>>> {
        return flow<FinalResponse<List<AgentData>>> {
            if (memoryCache.agents.isEmpty()) {
                val apiResponse = apiService.getAgents()
                memoryCache.agents.addAll(apiResponse.data
                    .sortedBy { it.displayName }
                    .filter { it.isPlayableCharacter })
            }
            emit(FinalResponse.Success(memoryCache.agents))
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
            if (memoryCache.currencies.isEmpty()) {
                val apiResponse = apiService.getCurrencies()
                memoryCache.currencies.addAll(
                    apiResponse.data.sortedBy { it.displayName }
                )
            }
            emit(FinalResponse.Success(memoryCache.currencies))
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

    override fun getMaps(): Flow<FinalResponse<List<MapsData>>> {
        return flow<FinalResponse<List<MapsData>>> {
            if (memoryCache.maps.isEmpty()) {
                val apiResponse = apiService.getMaps()
                memoryCache.maps.addAll(apiResponse.data.sortedBy { it.displayName })
            }
            emit(FinalResponse.Success(memoryCache.maps))
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
            if (memoryCache.weapons.isEmpty()) {
                val apiResponse = apiService.getWeapons()
                memoryCache.weapons.addAll(apiResponse.data.sortedBy { it.displayName })
            }
            emit(FinalResponse.Success(memoryCache.weapons))
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