package com.catnip.core.domain.usecase

import com.catnip.core.data.repository.IFeedsRepository
import com.catnip.core.data.source.resource.Resource
import com.catnip.core.domain.viewparam.FeedViewParam
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class FeedsInteractor(private val feedsRepository: IFeedsRepository) : FeedsUseCase {
    override fun getFeeds(): Flow<Resource<List<FeedViewParam>>> = feedsRepository.getFeeds()
    override fun getSearchFeeds(keywords: String): Flow<Resource<List<FeedViewParam>>> =
        feedsRepository.searchFeeds(keywords)

    override fun getFavoriteFeeds(): Flow<List<FeedViewParam>> = feedsRepository.getFavoriteFeeds()

    override fun getDetailFeed(idFeed: String): Flow<FeedViewParam> =
        feedsRepository.getDetailFeed(idFeed)

    override fun setFavoriteFeed(feed: FeedViewParam, state: Boolean) =
        feedsRepository.setFavoriteFeed(feed, state)
}