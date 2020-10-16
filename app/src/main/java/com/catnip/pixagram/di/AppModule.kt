package com.catnip.pixagram.di

import com.catnip.core.domain.usecase.FeedsInteractor
import com.catnip.core.domain.usecase.FeedsUseCase
import com.catnip.detail.DetailViewModel
import com.catnip.feeds.FeedsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

val useCaseModule = module {
    factory<FeedsUseCase> { FeedsInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DetailViewModel() }
    viewModel { FeedsViewModel(get()) }
}