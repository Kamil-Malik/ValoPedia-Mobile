package com.lelestacia.valorantgamepedia.data.model.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.Ability

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