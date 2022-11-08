package com.lelestacia.valorantgamepedia.data.model.local.maps_data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "map_table", primaryKeys = ["uuid", "display_name"])
data class LocalMapData(

    @ColumnInfo(name = "uuid") val localMapUUID: String,

    @ColumnInfo(name = "display_name") val localMapDisplayName: String,

    @ColumnInfo(name = "list_view_icon") val localMapListIcon: String,

    @ColumnInfo(name = "coordinates") val localMapCoordinate: String,

    @ColumnInfo(name = "display_icon") val localMapDisplayIcon: String,

    @ColumnInfo(name = "splash_icon")
    val localMapSplashIcon: String
)
