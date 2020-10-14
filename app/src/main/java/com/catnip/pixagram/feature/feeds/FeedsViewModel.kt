package com.catnip.pixagram.feature.feeds

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.core.domain.model.Feed
import com.catnip.core.domain.usecase.FeedsUseCase

class FeedsViewModel(private val feedsUseCase: FeedsUseCase) : ViewModel() {
    val feeds = feedsUseCase.getFeeds().asLiveData()

    fun setFavoriteFeed(feed : Feed, isFavorite:Boolean) =
        feedsUseCase.setFavoriteFeed(feed,isFavorite)


}