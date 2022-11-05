package com.lelestacia.valorantgamepedia.data.model.remote.weapons_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DamageRange(
    @SerializedName("bodyDamage")
    val bodyDamage: Int,
    @SerializedName("headDamage")
    val headDamage: Double,
    @SerializedName("legDamage")
    val legDamage: Double,
    @SerializedName("rangeEndMeters")
    val rangeEndMeters: Int,
    @SerializedName("rangeStartMeters")
    val rangeStartMeters: Int
) : Parcelable