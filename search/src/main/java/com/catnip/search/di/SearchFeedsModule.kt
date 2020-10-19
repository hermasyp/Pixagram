package com.catnip.search.di

import com.catnip.search.ui.SearchFeedsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
val searchFeedModule = module {
    viewModel { SearchFeedsViewModel(get()) }
}