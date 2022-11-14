package com.lelestacia.valorantgamepedia.data.repository.contract

interface SharedPrefRepository {

    fun getLisType(): Boolean

    fun setListType(isList: Boolean)
}