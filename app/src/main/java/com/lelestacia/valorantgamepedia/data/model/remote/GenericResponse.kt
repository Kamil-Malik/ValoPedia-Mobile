package com.lelestacia.valorantgamepedia.data.model.remote

import com.google.gson.annotations.SerializedName

data class GenericResponse <T> (

    @field:SerializedName("status")
    val status : Int,

    @field:SerializedName("data")
    val data : List<T>
)