package com.lelestacia.valorantgamepedia.data.repository

import com.lelestacia.valorantgamepedia.data.local.LocalDatabase
import com.lelestacia.valorantgamepedia.data.local.MemoryCache
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.LocalAgentData
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.relation.AgentWithAbility
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.entity.LocalMapData
import com.lelestacia.valorantgamepedia.data.model.mapper.ConvertAbility
import com.lelestacia.valorantgamepedia.data.model.mapper.ConvertAgent
import com.lelestacia.valorantgamepedia.data.model.mapper.ConvertMap
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.NetworkWeaponData
import com.lelestacia.valorantgamepedia.data.remote.ValorantApiSource
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import kotlinx.coroutines.*
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
                return@flow
            }

            val databaseAgent = agentDao.getListOfAgents()
            if (databaseAgent.isNotEmpty()) emit(FinalResponse.Success(databaseAgent))

            val apiResponse = apiService.getAgents()
            val networkAgentToLocalAgent = ConvertAgent().execute(apiResponse, coroutineDispatcher)

            if (databaseAgent != networkAgentToLocalAgent) {
                networkAgentToLocalAgent.onEach { agentDao.insertAgent(it) }
                ConvertAbility().execute(apiResponse, coroutineDispatcher)
                    .onEach { agentDao.insertAbility(it) }
                emit(FinalResponse.Success(networkAgentToLocalAgent))
            }

            memoryCache.localAgent.addAll(databaseAgent)
        }.flowOn(coroutineDispatcher).onStart { emit(FinalResponse.Loading) }.catch {
            when (this) {
                is IOException -> emit(FinalResponse.IoException)
                is HttpException -> emit(FinalResponse.GenericException(code(), message()))
                else -> emit(FinalResponse.GenericException(null, it.message))
            }
        }
    }

    override suspend fun getAgentByUUID(uuid: String): AgentWithAbility {
        return withContext(coroutineDispatcher) {
            agentDao.getAgentDetail(uuid)
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
                return@flow
            }

            val databaseMaps = mapDao.getMap()
            if (databaseMaps.isNotEmpty()) emit(FinalResponse.Success(databaseMaps))

            val apiResponse = apiService.getMaps()
            val networkMapsToLocalMaps = ConvertMap().execute(apiResponse, coroutineDispatcher)

            if (apiResponse != networkMapsToLocalMaps) {
                networkMapsToLocalMaps.onEach { mapDao.insert(it) }
                emit(FinalResponse.Success(networkMapsToLocalMaps))
            }

            memoryCache.localMaps.addAll(networkMapsToLocalMaps)
        }.flowOn(coroutineDispatcher).onStart { emit(FinalResponse.Loading) }.catch {
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
        }.flowOn(coroutineDispatcher).onStart { emit(FinalResponse.Loading) }.catch {
                when (this) {
                    is IOException -> emit(FinalResponse.IoException)
                    is HttpException -> emit(FinalResponse.GenericException(code(), message()))
                    else -> emit(FinalResponse.GenericException(null, it.message))
                }
            }
    }
}