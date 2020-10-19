package com.catnip.search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.core.data.source.resource.Resource
import com.catnip.core.domain.usecase.FeedsUseCase
import com.catnip.core.domain.viewparam.FeedViewParam

class SearchFeedsViewModel(private val feedsUseCase: FeedsUseCase) : ViewModel() {
    fun getSearchFeeds(keywords: String): LiveData<Resource<List<FeedViewParam>>> =
        feedsUseCase.getSearchFeeds(keywords = keywords).asLiveData()

    fun setFavoriteFeed(feed: FeedViewParam, isFavorite: Boolean) =
        feedsUseCase.setFavoriteFeed(feed, isFavorite)


}