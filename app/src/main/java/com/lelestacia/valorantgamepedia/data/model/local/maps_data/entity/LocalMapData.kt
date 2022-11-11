package com.lelestacia.valorantgamepedia.data.model.local.maps_data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "map_table", primaryKeys = ["maps_uuid", "maps_name"])
data class LocalMapData(

    @ColumnInfo(name = "maps_uuid") val uuid: String,

    @ColumnInfo(name = "maps_name") val displayName: String,

    @ColumnInfo(name = "map_list_icon") val listIcon: String,

    @ColumnInfo(name = "maps_coordinates") val displayCoordinate: String,

    @ColumnInfo(name = "maps_display_icon") val displayIcon: String,

    @ColumnInfo(name = "maps_splash_icon") val splashIcon: String
)
