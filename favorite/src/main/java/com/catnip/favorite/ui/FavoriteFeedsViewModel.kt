package com.catnip.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.catnip.core.domain.viewparam.FeedViewParam
import com.catnip.core.domain.usecase.FeedsUseCase

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class FavoriteFeedsViewModel(private val feedsUseCase: FeedsUseCase) : ViewModel(){
    val favoritedFeeds = feedsUseCase.getFavoriteFeeds().asLiveData()
    fun setFavoriteFeed(feed : FeedViewParam, isFavorite:Boolean) =
        feedsUseCase.setFavoriteFeed(feed,isFavorite)

}