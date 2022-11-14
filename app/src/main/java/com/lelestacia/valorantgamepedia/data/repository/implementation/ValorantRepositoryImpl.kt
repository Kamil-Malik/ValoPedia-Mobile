package com.lelestacia.valorantgamepedia.data.repository.implementation

import com.lelestacia.valorantgamepedia.data.data_source.local.LocalDatabase
import com.lelestacia.valorantgamepedia.data.model.local.agent.dao.AgentDao
import com.lelestacia.valorantgamepedia.data.model.local.agent.entities.Agent
import com.lelestacia.valorantgamepedia.data.model.local.agent.relation.AgentWithAbility
import com.lelestacia.valorantgamepedia.data.model.local.maps.dao.MapDao
import com.lelestacia.valorantgamepedia.data.model.local.maps.entity.Map
import com.lelestacia.valorantgamepedia.data.model.local.weapon.dao.WeaponDao
import com.lelestacia.valorantgamepedia.data.model.local.weapon.entity.Weapon
import com.lelestacia.valorantgamepedia.data.model.local.weapon.relation.WeaponDataWithDamageRangeAndSkin
import com.lelestacia.valorantgamepedia.data.model.mapper.MapToLocal
import com.lelestacia.valorantgamepedia.data.remote.ValorantApiSource
import com.lelestacia.valorantgamepedia.data.repository.contract.ValorantRepository
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ValorantRepositoryImpl @Inject constructor(
    private val apiService: ValorantApiSource,
    private val coroutineDispatcher: CoroutineDispatcher,
    localDatabase: LocalDatabase
) : ValorantRepository {

    private val mapDao: MapDao = localDatabase.mapDao()
    private val agentDao: AgentDao = localDatabase.agentDao()
    private val weaponDao: WeaponDao = localDatabase.weaponDao()

    private var isAgentUpdated: Boolean = false
    private var isMapUpdated: Boolean = false
    private var isWeaponUpdated: Boolean = false

    override fun getAgents(): Flow<FinalResponse<List<Agent>>> {
        return flow<FinalResponse<List<Agent>>> {
            val localData = agentDao.getListOfAgents()
            if (localData.isNotEmpty())
                emit(FinalResponse.Success(localData))

            if (isAgentUpdated) return@flow

            val apiResponse = apiService.getAgents()
            val agent = apiResponse
                .map { MapToLocal().agent(it) }
                .onEach { agentDao.insertAgent(it) }
            apiResponse
                .map { MapToLocal().ability(it, coroutineDispatcher) }
                .flatten()
                .onEach { agentDao.insertAbility(it) }

            isAgentUpdated = !isAgentUpdated
            if (localData != agent)
                emit(FinalResponse.Success(agent))

        }.onStart { emit(FinalResponse.Loading) }.catch {

            val localData = agentDao.getListOfAgents()
            if (localData.isNotEmpty()) {
                emit(FinalResponse.Success(localData))
                return@catch
            }

            when (this) {
                is IOException -> emit(FinalResponse.IoException)
                is HttpException -> emit(FinalResponse.HttpException(code(), message()))
                else -> emit(FinalResponse.GenericException(it.message))
            }
        }.flowOn(coroutineDispatcher)
    }

    override suspend fun getAgentByUUID(uuid: String): AgentWithAbility {
        return withContext(coroutineDispatcher) {
            agentDao.getAgentDetail(uuid)
        }
    }

    override fun getMaps(): Flow<FinalResponse<List<Map>>> {
        return flow<FinalResponse<List<Map>>> {

            val localData = mapDao.getMap()
            if (localData.isNotEmpty())
                emit(FinalResponse.Success(localData))

            if (isMapUpdated) return@flow

            val apiResponse = apiService
                .getMaps()
                .map { MapToLocal().map(it) }
                .onEach { mapDao.insert(it) }

            isMapUpdated = !isMapUpdated
            if (localData != apiResponse)
                emit(FinalResponse.Success(apiResponse))

        }.onStart { emit(FinalResponse.Loading) }.catch {

            val localData = mapDao.getMap()
            if (localData.isNotEmpty()) {
                emit(FinalResponse.Success(localData))
                return@catch
            }

            when (this) {
                is IOException -> emit(FinalResponse.IoException)
                is HttpException -> emit(FinalResponse.HttpException(code(), message()))
                else -> emit(FinalResponse.GenericException(it.message))
            }
        }.flowOn(coroutineDispatcher)
    }

    override fun getWeapons(): Flow<FinalResponse<List<Weapon>>> {
        return flow<FinalResponse<List<Weapon>>> {
            val localData = weaponDao.getListOfWeaponData()
            if (localData.isNotEmpty())
                emit(FinalResponse.Success(localData))

            if (isWeaponUpdated) return@flow

            val apiResponse = apiService.getWeapons()
            val weapon = apiResponse
                .map { MapToLocal().weapon(it) }
                .onEach { weaponDao.insertWeaponData(it) }
            apiResponse
                .map { MapToLocal().skin(it, coroutineDispatcher) }
                .flatten()
                .forEach { weaponDao.insertWeaponSkin(it) }
            apiResponse
                .map { MapToLocal().damageRange(it, coroutineDispatcher) }
                .flatten()
                .forEach { weaponDao.insertDamageRange(it) }
            apiResponse
                .map {
                    MapToLocal().statistic(it)
                }.forEach {
                    if (it != null)
                        weaponDao.insertWeaponStat(it)
                }

            isWeaponUpdated = !isWeaponUpdated
            if (localData != weapon)
                emit(FinalResponse.Success(weapon))

        }.onStart { emit(FinalResponse.Loading) }.catch {

            val localData = weaponDao.getListOfWeaponData()
            if (localData.isNotEmpty()) {
                emit(FinalResponse.Success(localData))
                return@catch
            }

            when (this) {
                is IOException -> emit(FinalResponse.IoException)
                is HttpException -> emit(FinalResponse.HttpException(code(), message()))
                else -> emit(FinalResponse.GenericException(it.message))
            }
        }.flowOn(coroutineDispatcher)
    }

    override suspend fun getWeaponDetail(uuid: String): WeaponDataWithDamageRangeAndSkin {
        return withContext(coroutineDispatcher) {
            weaponDao.getSpecificWeaponDetail(uuid)
        }
    }
}