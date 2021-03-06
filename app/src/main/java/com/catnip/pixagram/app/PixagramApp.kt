package com.catnip.pixagram.app

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.catnip.core.di.databaseModule
import com.catnip.core.di.networkModule
import com.catnip.core.di.repositoryModule
import com.catnip.detail.di.detailFeedModule
import com.catnip.feeds.di.feedsModule
import com.catnip.search.di.searchFeedModule
import com.catnip.pixagram.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class PixagramApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PixagramApp)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    feedsModule,
                    detailFeedModule,
                    searchFeedModule
                )
            )
        }
    }
}