package com.catnip.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.catnip.core.data.source.local.entity.FeedLocalEntity

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
@Database(entities = [FeedLocalEntity::class], version = 2, exportSchema = false)
abstract class FeedsDatabase : RoomDatabase() {
    abstract fun feedsDao(): FeedsDao
}