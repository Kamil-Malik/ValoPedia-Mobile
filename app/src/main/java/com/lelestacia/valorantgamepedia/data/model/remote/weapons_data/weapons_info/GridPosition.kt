package com.lelestacia.valorantgamepedia.data.model.remote.weapons_data.weapons_info


import com.google.gson.annotations.SerializedName

data class GridPosition(
    @SerializedName("column")
    val column: Int,
    @SerializedName("row")
    val row: Int
)