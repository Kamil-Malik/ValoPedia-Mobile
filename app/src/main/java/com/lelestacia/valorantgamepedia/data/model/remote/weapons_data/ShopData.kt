package com.lelestacia.valorantgamepedia.data.model.remote.weapons_data


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShopData(
    @SerializedName("assetPath")
    val assetPath: String,
    @SerializedName("canBeTrashed")
    val canBeTrashed: Boolean,
    @SerializedName("category")
    val category: String,
    @SerializedName("categoryText")
    val categoryText: String,
    @SerializedName("cost")
    val cost: Int,
    @SerializedName("newImage")
    val newImage: String
): Parcelable