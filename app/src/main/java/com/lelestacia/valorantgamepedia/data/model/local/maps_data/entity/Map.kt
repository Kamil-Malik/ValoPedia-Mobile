package com.lelestacia.valorantgamepedia.data.model.local.maps_data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "map_table", primaryKeys = ["uuid", "display_name"])
data class Map(

    @ColumnInfo(name = "uuid") val uuid: String,

    @ColumnInfo(name = "display_name") val displayName: String,

    @ColumnInfo(name = "list_icon") val listIcon: String,

    @ColumnInfo(name = "coordinates") val displayCoordinate: String,

    @ColumnInfo(name = "display_icon") val displayIcon: String,

    @ColumnInfo(name = "splash_icon") val splashIcon: String
)
