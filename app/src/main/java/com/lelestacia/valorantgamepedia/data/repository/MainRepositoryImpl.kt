package com.lelestacia.valorantgamepedia.data.repository

import com.lelestacia.valorantgamepedia.data.local.LocalDatabase
import com.lelestacia.valorantgamepedia.data.local.MemoryCache
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.entities.Agent
import com.lelestacia.valorantgamepedia.data.model.local.agent_data.relation.AgentWithAbility
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.entity.Map
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.entity.Weapon
import com.lelestacia.valorantgamepedia.data.model.local.weapon_data.relation.WeaponDataWithDamageRangeAndSkin
import com.lelestacia.valorantgamepedia.data.model.mapper.*
import com.lelestacia.valorantgamepedia.data.model.remote.currencies_data.NetworkCurrencyData
import com.lelestacia.valorantgamepedia.data.remote.ValorantApiSource
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
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
    private val weaponDao = localDatabase.weaponDao()

    private var isAgentUpdated: Boolean = false
    private var isWeaponUpdated: Boolean = false

    override fun getAgents(): Flow<FinalResponse<List<Agent>>> {
        return flow<FinalResponse<List<Agent>>> {
            val localData = agentDao.getListOfAgents()
            if (localData.isNotEmpty())
                emit(FinalResponse.Success(localData))

            if (isAgentUpdated) return@flow

            val apiResponse = apiService.getAgents()
            val agent = apiResponse
                .map { MapAgent().fromNetwork(it) }
                .onEach { agentDao.insertAgent(it) }
            apiResponse
                .map { MapAbility().fromNetwork(it, coroutineDispatcher) }
                .flatten()
                .onEach { agentDao.insertAbility(it) }

            isAgentUpdated = !isAgentUpdated
            if (localData != agent)
                emit(FinalResponse.Success(agent))

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

    override fun getMaps(): Flow<FinalResponse<List<Map>>> {
        return flow<FinalResponse<List<Map>>> {

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

    override fun getWeapons(): Flow<FinalResponse<List<Weapon>>> {
        return flow<FinalResponse<List<Weapon>>> {
            val localData = weaponDao.getListOfWeaponData()
            if (localData.isNotEmpty())
                emit(FinalResponse.Success(localData))

            if (isWeaponUpdated) return@flow

            val apiResponse = apiService.getWeapons()
            val weapon = apiResponse
                .map { MapWeapon().fromNetwork(it) }
                .onEach { weaponDao.insertWeaponData(it) }
            apiResponse
                .map { MapSkin().fromNetwork(it, coroutineDispatcher) }
                .flatten()
                .forEach { weaponDao.insertWeaponSkin(it) }
            apiResponse
                .map { MapDamageRange().fromNetwork(it, coroutineDispatcher) }
                .flatten()
                .forEach { weaponDao.insertDamageRange(it) }
            apiResponse
                .map {
                    MapStatistic().fromNetwork(it)
                }.forEach {
                    if (it != null)
                        weaponDao.insertWeaponStat(it)
                }

            isWeaponUpdated = !isWeaponUpdated
            if (localData != weapon)
                emit(FinalResponse.Success(weapon))

        }.flowOn(coroutineDispatcher).onStart { emit(FinalResponse.Loading) }.catch {
            when (this) {
                is IOException -> emit(FinalResponse.IoException)
                is HttpException -> emit(FinalResponse.GenericException(code(), message()))
                else -> emit(FinalResponse.GenericException(null, it.message))
            }
        }
    }

    override suspend fun getWeaponDetail(uuid: String): WeaponDataWithDamageRangeAndSkin {
        return withContext(coroutineDispatcher) {
            weaponDao.getSpecificWeaponDetail(uuid)
        }
    }
}