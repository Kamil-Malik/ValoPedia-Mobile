package com.lelestacia.valorantgamepedia.data.model.remote.maps_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NetworkMap(

    @SerializedName("callouts")
    val callouts: List<Callout>?,

    @SerializedName("coordinates")
    val coordinate: String,

    @SerializedName("displayIcon")
    val displayIcon: String?,

    @SerializedName("displayName")
    val displayName: String,

    @SerializedName("listViewIcon")
    val listIcon: String,

    @SerializedName("mapUrl")
    val networkMapURL: String,

    @SerializedName("splash")
    val splash: String,

    @SerializedName("uuid")
    val uuid: String,

    @SerializedName("xMultiplier")
    val xMultiplier: Double,

    @SerializedName("xScalarToAdd")
    val xScalarToAdd: Double,

    @SerializedName("yMultiplier")
    val yMultiplier: Double,

    @SerializedName("yScalarToAdd")
    val yScalarToAdd: Double
) : Parcelable