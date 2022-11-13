package com.lelestacia.valorantgamepedia.utility

sealed class FinalResponse<out T> {
    object Loading : FinalResponse<Nothing>()
    data class Success<out T>(val data: T) : FinalResponse<T>()
    data class HttpException(val code: Int?, val cause: String?) : FinalResponse<Nothing>()
    data class GenericException(val cause: String?): FinalResponse<Nothing>()
    object IoException : FinalResponse<Nothing>()
}
