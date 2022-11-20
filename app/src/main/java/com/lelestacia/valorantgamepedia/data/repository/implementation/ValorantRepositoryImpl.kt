package com.lelestacia.valorantgamepedia.data.repository.implementation

import com.lelestacia.valorantgamepedia.data.data_source.local.WikipediaDatabase
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
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class ValorantRepositoryImpl @Inject constructor(
    private val apiService: ValorantApiSource,
    private val coroutineDispatcher: CoroutineDispatcher,
    wikipediaDatabase: WikipediaDatabase
) : ValorantRepository {

    private val mapDao: MapDao = wikipediaDatabase.mapDao()
    private val agentDao: AgentDao = wikipediaDatabase.agentDao()
    private val weaponDao: WeaponDao = wikipediaDatabase.weaponDao()

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
                .also { agentDao.insertListOfAgent(it) }
            apiResponse
                .map { MapToLocal().ability(it, coroutineDispatcher) }
                .flatten()
                .also { agentDao.insertListOfAbility(it) }

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
                .also { mapDao.insertListOfMap(it) }

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
                .also { weaponDao.insertListOFWeaponData(it) }
            apiResponse
                .map { MapToLocal().skin(it, coroutineDispatcher) }
                .flatten()
                .also { weaponDao.insertListOfWeaponSkin(it) }
            apiResponse
                .map { MapToLocal().damageRange(it, coroutineDispatcher) }
                .flatten()
                .also { weaponDao.insertListOfDamageRange(it) }
            apiResponse
                .map {
                    MapToLocal().statistic(it)
                }.filterNotNull()
                .also { weaponDao.insertListOfWeaponStatistic(it) }

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
        }.flowOn(coroutineDispatcher).buffer()
    }

    override suspend fun getWeaponDetail(uuid: String): WeaponDataWithDamageRangeAndSkin {
        return withContext(coroutineDispatcher) {
            weaponDao.getSpecificWeaponDetail(uuid)
        }
    }
}