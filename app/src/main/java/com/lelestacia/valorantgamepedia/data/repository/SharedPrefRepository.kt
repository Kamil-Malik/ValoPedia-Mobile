package com.lelestacia.valorantgamepedia.data.repository

interface SharedPrefRepository {

    fun getLisType(): Boolean

    fun setListType(isList: Boolean)
}