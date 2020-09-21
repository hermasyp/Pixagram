package com.catnip.core.data.source.local.datasource

import com.catnip.core.data.source.local.entity.FeedItemEntity
import com.catnip.core.data.source.local.room.FeedsDao
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class FeedsLocalDataSource(private val feedsDao: FeedsDao) {
    fun getAllFeeds(): Flow<List<FeedItemEntity>> = feedsDao.getAllFeeds()

    fun getFavoriteFeeds(): Flow<List<FeedItemEntity>> = feedsDao.getFavoriteFeeds()

    suspend fun insertTourism(feeds: List<FeedItemEntity>) = feedsDao.insertFeeds(feeds)

    fun setFavoriteFeed(feed: FeedItemEntity, isFavorited: Boolean) {
        feed.isFavorite = isFavorited
        feedsDao.updateFavoriteFeeds(feed)
    }
}