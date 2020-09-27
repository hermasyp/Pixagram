package com.catnip.core.data.repository

import com.catnip.core.data.source.local.datasource.FeedsLocalDataSource
import com.catnip.core.data.source.remote.datasource.FeedsRemoteDataSource
import com.catnip.core.data.source.remote.network.ApiResponse
import com.catnip.core.data.source.remote.response.FeedItemResponse
import com.catnip.core.data.source.resource.NetworkBoundResource
import com.catnip.core.data.source.resource.Resource
import com.catnip.core.domain.model.Feed
import com.catnip.core.domain.repository.IFeedsRepository
import com.catnip.core.utils.AppExecutors
import com.catnip.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class FeedsRepository(
    private val remoteDataSource: FeedsRemoteDataSource,
    private val localDataSource: FeedsLocalDataSource,
    private val appExecutors: AppExecutors
) : IFeedsRepository {

    override fun getFeeds(): Flow<Resource<List<Feed>>> =
        object : NetworkBoundResource<List<Feed>, List<FeedItemResponse>>() {

            override fun loadFromDB(): Flow<List<Feed>> {
                return localDataSource.getAllFeeds().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Feed>?): Boolean = (data == null || data.isEmpty())

            override suspend fun createCall(): Flow<ApiResponse<List<FeedItemResponse>>> =
                remoteDataSource.getFeeds()

            override suspend fun saveCallResult(data: List<FeedItemResponse>) {
                val feedList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertFeeds(feedList)
            }
        }.asFlow()


    override fun getFavoriteFeeds(): Flow<List<Feed>> {
        return localDataSource.getFavoriteFeeds().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteFeed(feed: Feed, state: Boolean) {
        val feedEntity = DataMapper.mapDomainToEntity(feed)
        appExecutors.diskIO().execute { localDataSource.setFavoriteFeed(feedEntity, state) }
    }
}