package com.lelestacia.valorantgamepedia.data.repository

import com.lelestacia.valorantgamepedia.data.api.ValorantApi
import com.lelestacia.valorantgamepedia.data.local.LocalDatabase
import com.lelestacia.valorantgamepedia.data.local.MemoryCache
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.LocalAgentData
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.LocalMapData
import com.lelestacia.valorantgamepedia.data.model.mapper.NetworkToLocal
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.RemoteAgentData
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeaponData
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ValorantApi,
    private val coroutineDispatcher: CoroutineDispatcher,
    localDatabase: LocalDatabase
) : MainRepository {

    private val memoryCache = MemoryCache
    private val mapDao = localDatabase.mapDao()
    private val agentDao = localDatabase.agentDao()

    override fun getAgents(): Flow<FinalResponse<List<RemoteAgentData>>> {
        return flow<FinalResponse<List<RemoteAgentData>>> {
//            var localAgent = emptyList<LocalAgentData>()
//            if (localAgent.isEmpty()) {
//                val apiResponse = apiService.getAgents().data
//                localAgent = NetworkToLocal().convertAgentResponse(apiResponse, coroutineDispatcher)
//                    .onEach { agentDao.insert(it) }
//                emit(FinalResponse.Success(apiResponse))
//            } else {
//                emit(FinalResponse.Success(localAgent))
//                val apiResponse = apiService.getAgents().data
//                localAgent = NetworkToLocal().convertAgentResponse(apiResponse, coroutineDispatcher)
//                    .onEach { agentDao.insert(it) }
//            }
//            emit(FinalResponse.Success(localAgent))
            val apiResponse = apiService.getAgents().data
            emit(FinalResponse.Success(apiResponse))
        }.flowOn(coroutineDispatcher).onStart {
            emit(FinalResponse.Loading)
        }.catch {
            when (this) {
                is IOException -> emit(FinalResponse.IoException)
                is HttpException -> emit(FinalResponse.GenericException(code(), message()))
                else -> emit(FinalResponse.GenericException(null, it.message))
            }
        }
    }

    override fun getCurrencies(): Flow<FinalResponse<List<NetworkCurrencyData>>> {
        return flow<FinalResponse<List<NetworkCurrencyData>>> {
            if (memoryCache.currencies.isEmpty()) {
                val apiResponse = apiService.getCurrencies()
                memoryCache.currencies.addAll(apiResponse.data.sortedBy { it.displayName })
            }
            emit(FinalResponse.Success(memoryCache.currencies))
        }.flowOn(coroutineDispatcher).onStart { emit(FinalResponse.Loading) }.catch {
            when (this) {
                is IOException -> emit(FinalResponse.IoException)
                is HttpException -> emit(FinalResponse.GenericException(code(), message()))
                else -> emit(FinalResponse.GenericException(null, it.message))
            }
        }
    }

    override fun getMaps(): Flow<FinalResponse<List<LocalMapData>>> {
        return flow<FinalResponse<List<LocalMapData>>> {
            var localMaps = mapDao.getMap()
            if (localMaps.isEmpty()) {
                val apiResponse = apiService.getMaps().data
                localMaps = NetworkToLocal().convertMapResponse(apiResponse, coroutineDispatcher)
                    .onEach { map ->
                        mapDao.insert(map)
                    }
            } else {
                emit(FinalResponse.Success(localMaps))
                val apiResponse = apiService.getMaps().data
                localMaps = NetworkToLocal().convertMapResponse(apiResponse, coroutineDispatcher)
                    .onEach { map ->
                        mapDao.insert(map)
                    }
            }
            emit(FinalResponse.Success(localMaps))
        }
            .flowOn(coroutineDispatcher)
            .onStart { emit(FinalResponse.Loading) }
            .catch {
                when (this) {
                    is IOException -> emit(FinalResponse.IoException)
                    is HttpException -> emit(FinalResponse.GenericException(code(), message()))
                    else -> emit(FinalResponse.GenericException(null, it.message))
                }
            }
    }

    override fun getWeapons(): Flow<FinalResponse<List<NetworkWeaponData>>> {
        return flow<FinalResponse<List<NetworkWeaponData>>> {
            if (memoryCache.weapons.isEmpty()) {
                val apiResponse = apiService.getWeapons()
                memoryCache.weapons.addAll(apiResponse.data.sortedBy { it.displayName })
            }
            emit(FinalResponse.Success(memoryCache.weapons))
        }.flowOn(coroutineDispatcher).onStart { emit(FinalResponse.Loading) }.catch {
            when (this) {
                is IOException -> emit(FinalResponse.IoException)
                is HttpException -> emit(FinalResponse.GenericException(code(), message()))
                else -> emit(FinalResponse.GenericException(null, it.message))
            }
        }
    }
}