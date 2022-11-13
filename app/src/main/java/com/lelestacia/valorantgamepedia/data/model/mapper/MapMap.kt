package com.lelestacia.valorantgamepedia.data.model.mapper

import com.lelestacia.valorantgamepedia.data.model.local.maps_data.entity.Map
import com.lelestacia.valorantgamepedia.data.model.remote.maps_data.NetworkMap

class MapMap {

    fun fromNetwork(networkMap: NetworkMap): Map {
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