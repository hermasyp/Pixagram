package com.catnip.favorite.di

import com.catnip.favorite.ui.FavoriteFeedsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

val favoriteModule = module {
    viewModel { FavoriteFeedsViewModel(get()) }
}