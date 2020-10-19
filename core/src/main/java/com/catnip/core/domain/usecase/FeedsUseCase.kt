package com.catnip.core.domain.usecase

import com.catnip.core.data.source.resource.Resource
import com.catnip.core.domain.viewparam.FeedViewParam
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
interface FeedsUseCase {
    fun getFeeds(): Flow<Resource<List<FeedViewParam>>>

    fun getSearchFeeds(keywords: String): Flow<Resource<List<FeedViewParam>>>

    fun getFavoriteFeeds(): Flow<List<FeedViewParam>>

    fun getDetailFeed(idFeed: String): Flow<FeedViewParam>

    fun setFavoriteFeed(feed: FeedViewParam, state: Boolean)
}