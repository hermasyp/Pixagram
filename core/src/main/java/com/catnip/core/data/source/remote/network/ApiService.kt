package com.catnip.core.data.source.remote.network

import com.catnip.core.BuildConfig
import com.catnip.core.data.source.remote.response.PixabayResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface ApiService {
    @GET("api")
    suspend fun getFeeds(
        @Query("key") key: String = BuildConfig.PIXABAY_KEY,
        @Query("per_page") perPage: Int = 100
    ): PixabayResponse

    @GET("api")
    suspend fun searchFeeds(
        @Query("key") key: String = BuildConfig.PIXABAY_KEY,
        @Query("q") keywords: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 100
    ): PixabayResponse
}