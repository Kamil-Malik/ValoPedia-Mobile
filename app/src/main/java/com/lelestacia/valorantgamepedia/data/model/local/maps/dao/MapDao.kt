package com.lelestacia.valorantgamepedia.data.model.local.maps.dao

import androidx.room.*
import com.lelestacia.valorantgamepedia.data.model.local.maps.entity.Map

@Dao
interface MapDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListOfMap(map: List<Map>)

    @Query("Select * from map_table ORDER BY name")
    fun getMap(): List<Map>
}