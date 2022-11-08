package com.lelestacia.valorantgamepedia.data.model.local.maps_data

import androidx.room.*

@Dao
interface MapDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(map: LocalMapData)

    @Update
    suspend fun update(map: LocalMapData)

    @Query("Select * from map_table ORDER BY display_name")
    fun getMap(): List<LocalMapData>
}