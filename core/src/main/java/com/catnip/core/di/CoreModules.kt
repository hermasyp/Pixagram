package com.catnip.core.di

import androidx.room.Room
import com.catnip.core.BuildConfig
import com.catnip.core.data.repository.FeedsRepository
import com.catnip.core.data.source.local.datasource.FeedsLocalDataSource
import com.catnip.core.data.source.local.room.FeedsDatabase
import com.catnip.core.data.source.remote.datasource.FeedsRemoteDataSource
import com.catnip.core.data.source.remote.network.ApiService
import com.catnip.core.domain.repository.IFeedsRepository
import com.catnip.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/

val databaseModule = module {
    factory { get<FeedsDatabase>().feedsDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            FeedsDatabase::class.java, "Feeds.db"
        ).fallbackToDestructiveMigration().build()
    }
}
val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { FeedsLocalDataSource(get()) }
    single { FeedsRemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IFeedsRepository> {
        FeedsRepository(
            get(),
            get(),
            get()
        )
    }
}