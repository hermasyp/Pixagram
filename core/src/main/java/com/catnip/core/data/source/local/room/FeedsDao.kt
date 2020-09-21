package com.catnip.core.data.source.local.room

import androidx.room.*
import com.catnip.core.data.source.local.entity.FeedItemEntity
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
@Dao
interface FeedsDao {

    @Query("SELECT * FROM FEEDS")
    fun getAllFeeds(): Flow<List<FeedItemEntity>>

    @Query("SELECT * FROM FEEDS where isFavorite = 1")
    fun getFavoriteFeeds(): Flow<List<FeedItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeeds(feeds: List<FeedItemEntity>)

    @Update
    fun updateFavoriteFeeds(feed: FeedItemEntity)

}