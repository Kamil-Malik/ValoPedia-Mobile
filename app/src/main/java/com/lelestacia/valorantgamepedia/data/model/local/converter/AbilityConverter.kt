package com.lelestacia.valorantgamepedia.data.model.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.lelestacia.valorantgamepedia.data.model.remote.agent_data.Ability

class AbilityConverter {

    @TypeConverter
    fun listOfAbilityToString(ability: List<Ability>): String {
        val type = object : TypeToken<List<Ability>>() {}.type
        return Gson().toJson(ability, type)
    }

    @TypeConverter
    fun stringToListOfAbility(ability: String): List<Ability> {
        val type = object : TypeToken<List<Ability>>() {}.type
        return Gson().fromJson(ability, type)
    }
}