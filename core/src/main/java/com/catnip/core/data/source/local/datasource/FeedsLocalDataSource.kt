package com.catnip.core.data.source.local.datasource

import com.catnip.core.data.source.local.entity.FeedLocalEntity
import com.catnip.core.data.source.local.room.FeedsDao
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class FeedsLocalDataSource(private val feedsDao: FeedsDao) {
    fun getAllFeeds(): Flow<List<FeedLocalEntity>> = feedsDao.getAllFeeds()

    fun getFavoriteFeeds(): Flow<List<FeedLocalEntity>> = feedsDao.getFavoriteFeeds()

    fun getDetailFeed(idFeed: String): Flow<FeedLocalEntity> = feedsDao.getDetailFeeds(idFeed)

    fun setFavoriteFeed(feed: FeedLocalEntity, isFavorite: Boolean) {
        feed.isFavorite = isFavorite
        feedsDao.updateFavoriteFeeds(feed)
    }

    suspend fun insertFeeds(feeds: List<FeedLocalEntity>) = feedsDao.insertFeeds(feeds)
}