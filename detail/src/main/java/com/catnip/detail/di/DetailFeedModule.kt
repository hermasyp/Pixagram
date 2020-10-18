package com.catnip.detail.di

import com.catnip.detail.ui.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/


val detailFeedModule = module {
    viewModel { DetailViewModel(get()) }
}