package com.catnip.core.domain.repository

import com.catnip.core.data.source.resource.Resource
import com.catnip.core.domain.model.Feed
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

interface IFeedsRepository {
    fun getFeeds(): Flow<Resource<List<Feed>>>

    fun getFavoriteFeeds(): Flow<List<Feed>>

    fun setFavoriteFeed(feed: Feed, state: Boolean)
}