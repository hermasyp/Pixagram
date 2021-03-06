package com.catnip.core.data.source.remote.datasource

import android.util.Log
import com.catnip.core.data.source.remote.entity.FeedResponseEntity
import com.catnip.core.data.source.remote.network.ApiResponse
import com.catnip.core.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

class FeedsRemoteDataSource(private val apiService: ApiService) {

    suspend fun getFeeds(): Flow<ApiResponse<List<FeedResponseEntity>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getFeeds()
                val dataArray = response.hits
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.hits))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSearchFeeds(keywords: String): Flow<ApiResponse<List<FeedResponseEntity>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.searchFeeds(keywords = keywords)
                val dataArray = response.hits
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.hits))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

