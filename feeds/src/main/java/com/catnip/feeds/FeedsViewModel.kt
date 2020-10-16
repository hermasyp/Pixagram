package com.catnip.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.core.domain.viewparam.FeedViewParam
import com.catnip.core.domain.usecase.FeedsUseCase

class FeedsViewModel(private val feedsUseCase: FeedsUseCase) : ViewModel() {
    val feeds = feedsUseCase.getFeeds().asLiveData()

    fun setFavoriteFeed(feed : FeedViewParam, isFavorite:Boolean) =
        feedsUseCase.setFavoriteFeed(feed,isFavorite)


}