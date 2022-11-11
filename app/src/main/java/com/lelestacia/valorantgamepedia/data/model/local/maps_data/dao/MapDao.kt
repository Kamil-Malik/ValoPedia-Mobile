package com.lelestacia.valorantgamepedia.data.model.local.maps_data.dao

import androidx.room.*
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.entity.LocalMapData

@Dao
interface MapDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(map: LocalMapData)

    @Update
    suspend fun update(map: LocalMapData)

    @Query("Select * from map_table ORDER BY maps_name")
    fun getMap(): List<LocalMapData>
}