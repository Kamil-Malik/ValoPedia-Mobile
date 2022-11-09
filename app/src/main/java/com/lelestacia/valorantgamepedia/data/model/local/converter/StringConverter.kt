package com.lelestacia.valorantgamepedia.data.model.local.converter

import androidx.room.TypeConverter

class StringConverter {

    @TypeConverter
    fun stringToList(stringParam: String): List<String> {
        return stringParam.split(",").map { it }
    }

    @TypeConverter
    fun listToString(listParam: List<String>): String {
        return listParam.joinToString(separator = ",")
    }
}