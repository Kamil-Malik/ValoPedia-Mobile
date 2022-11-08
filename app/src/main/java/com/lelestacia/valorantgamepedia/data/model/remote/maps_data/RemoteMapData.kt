package com.lelestacia.valorantgamepedia.data.model.remote.maps_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RemoteMapData(
    @SerializedName("callouts")
    val callouts: List<Callout>?,
    @SerializedName("coordinates")
    val networkMapCoordinate: String,
    @SerializedName("displayIcon")
    val networkMapDisplayIcon: String?,
    @SerializedName("displayName")
    val networkMapDisplayName: String,
    @SerializedName("listViewIcon")
    val networkMapListIcon: String,
    @SerializedName("mapUrl")
    val networkMapURL: String,
    @SerializedName("splash")
    val networkMapSplash: String,
    @SerializedName("uuid")
    val networkMapUUID: String,
    @SerializedName("xMultiplier")
    val xMultiplier: Double,
    @SerializedName("xScalarToAdd")
    val xScalarToAdd: Double,
    @SerializedName("yMultiplier")
    val yMultiplier: Double,
    @SerializedName("yScalarToAdd")
    val yScalarToAdd: Double
) : Parcelable