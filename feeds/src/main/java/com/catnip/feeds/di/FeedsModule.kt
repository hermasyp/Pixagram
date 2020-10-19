package com.catnip.feeds.di

import com.catnip.feeds.ui.FeedsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

val feedsModule = module {
    viewModel { FeedsViewModel(get()) }
}