package com.lelestacia.valorantgamepedia.data.repository

import com.lelestacia.valorantgamepedia.data.api.ValorantApi
import com.lelestacia.valorantgamepedia.data.model.remote.AgentData
import com.lelestacia.valorantgamepedia.utility.FinalResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ValorantApi, private val coroutineDispatcher: CoroutineDispatcher
) : MainRepository {

    override fun getAgents(): Flow<FinalResponse<List<AgentData>>> {
        return flow<FinalResponse<List<AgentData>>> {
            val response = apiService.getAgents()
            emit(
                FinalResponse.Success(
                    response.data
                )
            )
        }.flowOn(coroutineDispatcher)
            .onStart {
                emit(FinalResponse.Loading)
            }.catch {
                when (this) {
                    is IOException -> emit(FinalResponse.IoException)
                    is HttpException -> emit(FinalResponse.GenericException(code(), message()))
                    else -> emit(FinalResponse.GenericException(null, it.message))
                }
            }
    }
}