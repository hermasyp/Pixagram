package com.catnip.core.data.source.local.room

import androidx.room.*
import com.catnip.core.data.source.local.entity.FeedLocalEntity
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
@Dao
interface FeedsDao {

    @Query("SELECT * FROM FEEDS")
    fun getAllFeeds(): Flow<List<FeedLocalEntity>>

    @Query("SELECT * FROM FEEDS where isFavorite = 1")
    fun getFavoriteFeeds(): Flow<List<FeedLocalEntity>>

    @Query("SELECT * FROM FEEDS where  tags LIKE '%' || :query || '%'")
    fun searchFeeds(query : String): Flow<List<FeedLocalEntity>>

    @Query("SELECT * FROM FEEDS where id = :idFeed")
    fun getDetailFeeds(idFeed : String): Flow<FeedLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFeeds(feeds: List<FeedLocalEntity>)

    @Update
    fun updateFavoriteFeeds(feed: FeedLocalEntity)

}