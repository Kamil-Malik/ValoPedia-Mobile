package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.maps_data.entity.Map
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.NetworkMap
import kotlinx.coroutines.*

class ConvertMap {

    suspend fun execute(
        networkMapData: List<NetworkMap>,
        coroutineDispatcher: CoroutineDispatcher
    ): List<Map> {
        val arr = arrayListOf<Deferred<Map>>()
        networkMapData.forEach {
            arr.add(
                CoroutineScope(coroutineDispatcher).async {
                    convertMap(it)
                }
            )
        }
        return arr.awaitAll()
    }

    private fun convertMap(networkMap: NetworkMap) : Map {
        return Map(
            uuid = networkMap.uuid,
            displayName = networkMap.displayName,
            listIcon = networkMap.listIcon,
            displayCoordinate = networkMap.coordinate,
            displayIcon = networkMap.displayIcon ?: "",
            splashIcon = networkMap.splash
        )
    }
}