package com.catnip.core.data.repository

import com.catnip.core.data.source.resource.Resource
import com.catnip.core.domain.viewparam.FeedViewParam
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

interface IFeedsRepository {
    fun getFeeds(): Flow<Resource<List<FeedViewParam>>>

    fun getFavoriteFeeds(): Flow<List<FeedViewParam>>

    fun getDetailFeed(idFeed: String): Flow<FeedViewParam>

    fun searchFeeds(keywords: String): Flow<Resource<List<FeedViewParam>>>

    fun setFavoriteFeed(feed: FeedViewParam, state: Boolean)
}