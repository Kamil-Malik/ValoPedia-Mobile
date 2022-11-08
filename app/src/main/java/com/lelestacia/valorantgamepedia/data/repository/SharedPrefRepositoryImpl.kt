package com.lelestacia.valorantgamepedia.data.repository

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class SharedPrefRepositoryImpl @Inject constructor(
   context: Context
) : SharedPrefRepository {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    override fun getLisType(): Boolean {
        return sharedPref.getBoolean(WEAPON_LIST_TYPE, false)
    }

    override fun setListType(isList: Boolean) {
        sharedPref.edit {
            putBoolean(WEAPON_LIST_TYPE, isList)
            apply()
        }
    }

    companion object {
        private const val PREF_NAME = "UserPref"
        private const val WEAPON_LIST_TYPE = "IsList"
    }
}