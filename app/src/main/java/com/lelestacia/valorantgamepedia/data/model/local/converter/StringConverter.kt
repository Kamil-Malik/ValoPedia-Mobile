package com.lelestacia.valorantgamepedia.data.model.local.converter

import androidx.room.TypeConverter

class StringConverter {

    @TypeConverter
    fun stringToList(stringParam: String): List<String> {
        if (stringParam.isEmpty())
            return emptyList()
        return stringParam.split(",").map { it }
    }

    @TypeConverter
    fun listToString(listParam: List<String>): String {
        if (listParam.isEmpty())
            return ""
        return listParam.joinToString(separator = ",")
    }
}