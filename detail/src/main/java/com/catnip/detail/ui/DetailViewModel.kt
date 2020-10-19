package com.catnip.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.core.domain.usecase.FeedsUseCase
import com.catnip.core.domain.viewparam.FeedViewParam

class DetailViewModel(private val feedsUseCase: FeedsUseCase) : ViewModel() {

    fun getDetailFeed(feedsId : String) : LiveData<FeedViewParam> {
        return feedsUseCase.getDetailFeed(feedsId).asLiveData()
    }

    fun setFavoriteFeed(feed : FeedViewParam, isFavorite:Boolean) =
        feedsUseCase.setFavoriteFeed(feed,isFavorite)

}