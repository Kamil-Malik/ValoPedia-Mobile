package com.lelestacia.valorantgamepedia.data.repository

import com.lelestacia.valorantgamepedia.data.local.LocalDatabase
import com.lelestacia.valorantgamepedia.data.local.MemoryCache
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAgentData
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.LocalMapData
import com.lelestacia.valorantgamepedia.data.model.mapper.ConvertAbility
import com.lelestacia.valorantgamepedia.data.model.mapper.ConvertAgent
import com.lelestacia.valorantgamepedia.data.model.mapper.ConvertMap
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeaponData
import com.lelestacia.valorantgamepedia.data.remote.ValorantApiSource
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ValorantApiSource,
    private val coroutineDispatcher: CoroutineDispatcher,
    localDatabase: LocalDatabase
) : MainRepository {

    private val memoryCache = MemoryCache
    private val mapDao = localDatabase.mapDao()
    private val agentDao = localDatabase.agentDao()

    override fun getAgents(): Flow<FinalResponse<List<LocalAgentData>>> {
        return flow<FinalResponse<List<LocalAgentData>>> {
            if (memoryCache.localAgent.isNotEmpty()) {
                emit(FinalResponse.Success(memoryCache.localAgent))
            } else {
                val localAgentData = agentDao.getListOfAgents()
                if (localAgentData.isNotEmpty()) {
                    emit(FinalResponse.Success(localAgentData))
                    val apiResponse = apiService.getAgents()
                    ConvertAbility().execute(apiResponse, coroutineDispatcher)
                        .onEach { agentDao.insertAbility(it) }
                    ConvertAgent().execute(apiResponse, coroutineDispatcher)
                        .onEach { agentDao.insertAgent(it) }
                    emit(FinalResponse.Success(localAgentData))
                } else {
                    val apiResponse = apiService.getAgents()
                    ConvertAbility().execute(apiResponse, coroutineDispatcher)
                        .onEach { agentDao.insertAbility(it) }
                    val agentData = ConvertAgent().execute(apiResponse, coroutineDispatcher)
                        .onEach { agentDao.insertAgent(it) }
                    memoryCache.localAgent.addAll(agentData)
                }
            }


            var localAgents = memoryCache.localAgent as List<LocalAgentData>
            localAgents =
                if (localAgents.isEmpty()) {
                    val apiResponse = apiService.getAgents()
                    ConvertAbility().execute(apiResponse, coroutineDispatcher)
                        .onEach { agentDao.insertAbility(it) }
                    ConvertAgent().execute(apiResponse, coroutineDispatcher)
                        .onEach { agentDao.insertAgent(it) }
                } else {
                    emit(FinalResponse.Success(localAgents))
                    val apiResponse = apiService.getAgents()
                    ConvertAbility().execute(apiResponse, coroutineDispatcher)
                        .onEach { agentDao.insertAbility(it) }
                    ConvertAgent().execute(apiResponse, coroutineDispatcher)
                        .onEach { agentDao.insertAgent(it) }
                }
            emit(FinalResponse.Success(localAgents))
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
                memoryCache.currencies.addAll(apiResponse)
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
            if (memoryCache.localMaps.isNotEmpty()) {
                emit(FinalResponse.Success(memoryCache.localMaps))
            } else {
                var dbMaps = mapDao.getMap()
                if (dbMaps.isNotEmpty()) {
                    emit(FinalResponse.Success(dbMaps))
                }
                dbMaps = ConvertMap()
                    .execute(apiService.getMaps(), coroutineDispatcher)
                    .onEach {
                        mapDao.insert(it)
                    }
                memoryCache.localMaps.apply {
                    clear()
                    addAll(dbMaps)
                }
                emit(FinalResponse.Success(dbMaps))
            }

            var localMaps = mapDao.getMap()
            localMaps =
                if (localMaps.isEmpty()) {
                    val apiResponse = apiService.getMaps()
                    ConvertMap().execute(apiResponse, coroutineDispatcher)
                        .onEach { map ->
                            mapDao.insert(map)
                        }
                } else {
                    emit(FinalResponse.Success(localMaps))
                    val apiResponse = apiService.getMaps()
                    ConvertMap().execute(apiResponse, coroutineDispatcher)
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
                memoryCache.weapons.addAll(apiResponse)
            }
            emit(FinalResponse.Success(memoryCache.weapons))
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
}