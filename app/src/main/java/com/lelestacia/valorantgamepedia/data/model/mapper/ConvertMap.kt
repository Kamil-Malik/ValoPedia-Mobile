package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.maps_data.LocalMapData
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.RemoteMapData
import kotlinx.coroutines.*

class ConvertMap {

    suspend fun execute(
        remoteMapData: List<RemoteMapData>,
        coroutineDispatcher: CoroutineDispatcher
    ): List<LocalMapData> {
        val arr = arrayListOf<Deferred<LocalMapData>>()
        remoteMapData.forEach {
            arr.add(
                CoroutineScope(coroutineDispatcher).async {
                    convertMap(it)
                }
            )
        }
        return arr.awaitAll()
    }

    private fun convertMap(remoteMapData: RemoteMapData) : LocalMapData {
        return LocalMapData(
            localMapUUID = remoteMapData.networkMapUUID,
            localMapDisplayName = remoteMapData.networkMapDisplayName,
            localMapListIcon = remoteMapData.networkMapListIcon,
            localMapCoordinate = remoteMapData.networkMapCoordinate,
            localMapDisplayIcon = remoteMapData.networkMapDisplayIcon ?: "",
            localMapSplashIcon = remoteMapData.networkMapSplash
        )
    }
}