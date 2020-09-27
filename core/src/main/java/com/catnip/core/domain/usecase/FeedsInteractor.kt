package com.catnip.core.domain.usecase

import com.catnip.core.data.repository.FeedsRepository
import com.catnip.core.data.source.resource.Resource
import com.catnip.core.domain.model.Feed
import com.catnip.core.domain.repository.IFeedsRepository
import kotlinx.coroutines.flow.Flow

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class FeedsInteractor (private val feedsRepository: IFeedsRepository) : FeedsUseCase{
    override fun getFeeds(): Flow<Resource<List<Feed>>> = feedsRepository.getFeeds()

    override fun getFavoriteFeeds(): Flow<List<Feed>> = feedsRepository.getFavoriteFeeds()

    override fun setFavoriteFeed(feed: Feed, state: Boolean) = feedsRepository.setFavoriteFeed(feed,state)
}