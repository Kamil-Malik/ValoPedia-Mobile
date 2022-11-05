package com.lelestacia.valorantgamepedia.data.model.remote.weapons_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GridPosition(
    @SerializedName("column")
    val column: Int,
    @SerializedName("row")
    val row: Int
) : Parcelable