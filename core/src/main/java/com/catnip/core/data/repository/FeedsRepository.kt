package com.catnip.core.data.repository

import com.catnip.core.data.source.local.datasource.FeedsLocalDataSource
import com.catnip.core.data.source.pref.datasource.PrefDataSource
import com.catnip.core.data.source.remote.datasource.FeedsRemoteDataSource
import com.catnip.core.data.source.remote.entity.FeedResponseEntity
import com.catnip.core.data.source.remote.network.ApiResponse
import com.catnip.core.data.source.resource.NetworkBoundResource
import com.catnip.core.data.source.resource.Resource
import com.catnip.core.domain.viewparam.FeedViewParam
import com.catnip.core.utils.AppExecutors
import com.catnip.core.utils.DataMapper
import com.catnip.core.utils.getCurrentDateTime
import com.catnip.core.utils.toFormattedString
import kotlinx.coroutines.flow.*

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class FeedsRepository(
    private val remoteDataSource: FeedsRemoteDataSource,
    private val localDataSource: FeedsLocalDataSource,
    private val prefDataSource: PrefDataSource,
    private val appExecutors: AppExecutors
) : IFeedsRepository {

    override fun getFeeds(): Flow<Resource<List<FeedViewParam>>> =
        object : NetworkBoundResource<List<FeedViewParam>, List<FeedResponseEntity>>() {

            override fun loadFromDB(): Flow<List<FeedViewParam>> {
                return localDataSource.getAllFeeds().map {
                    DataMapper.mapEntitiesToViewParams(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<FeedResponseEntity>>> =
                remoteDataSource.getFeeds()

            //todo compare timestamp , fetch per one day
            override fun shouldFetch(data: List<FeedViewParam>?): Boolean {
                val lastFetch = prefDataSource.lastFetch
                val currentDate = getCurrentDateTime().toFormattedString("yyyy/MM/dd")
                return data.isNullOrEmpty() || !lastFetch.equals(currentDate, true)
            }

            override suspend fun saveCallResult(data: List<FeedResponseEntity>) {
                val feedList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertFeeds(feedList)
                //todo save last fetch timestamp
                prefDataSource.lastFetch = getCurrentDateTime().toFormattedString("yyyy/MM/dd")

            }
        }.asFlow()


    override fun getFavoriteFeeds(): Flow<List<FeedViewParam>> {
        return localDataSource.getFavoriteFeeds().map {
            DataMapper.mapEntitiesToViewParams(it)
        }
    }

    override fun getDetailFeed(idFeed: String): Flow<FeedViewParam> {
        return localDataSource.getDetailFeed(idFeed).map {
            DataMapper.mapEntityToViewParam(it)
        }
    }

    override fun searchFeeds(keywords: String): Flow<Resource<List<FeedViewParam>>> = flow {
        emit(Resource.Loading())
        val response = remoteDataSource.getSearchFeeds(keywords)
        when (val apiResponse = response.first()) {
            is ApiResponse.Success -> {
                emitAll(flow {
                    apiResponse.data.map {
                        Resource.Success(
                            it
                        )
                    }
                })
            }
            is ApiResponse.Empty -> {
                emitAll(flow {
                    Resource.Success(
                        listOf<FeedViewParam>()
                    )
                })
            }
            is ApiResponse.Error -> {
                emit(
                    Resource.Error(
                        apiResponse.errorMessage
                    )
                )
            }
        }
    }

    override fun setFavoriteFeed(feed: FeedViewParam, state: Boolean) {
        val feedEntity = DataMapper.mapViewParamToEntity(feed)
        appExecutors.diskIO().execute { localDataSource.setFavoriteFeed(feedEntity, state) }
    }
}