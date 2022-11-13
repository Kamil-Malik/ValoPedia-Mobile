package com.lelestacia.valorantgamepedia.data.model.local.maps_data.dao

import androidx.room.*
import com.lelestacia.valorantgamepedia.data.model.local.maps_data.entity.Map

@Dao
interface MapDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(map: Map)

    @Query("Select * from map_table ORDER BY display_name")
    fun getMap(): List<Map>
}