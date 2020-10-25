package com.catnip.core.di

import androidx.room.Room
import com.catnip.core.BuildConfig
import com.catnip.core.data.repository.FeedsRepository
import com.catnip.core.data.source.local.datasource.FeedsLocalDataSource
import com.catnip.core.data.source.local.room.FeedsDatabase
import com.catnip.core.data.source.remote.datasource.FeedsRemoteDataSource
import com.catnip.core.data.source.remote.network.ApiService
import com.catnip.core.data.repository.IFeedsRepository
import com.catnip.core.data.source.pref.datasource.PrefDataSource
import com.catnip.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
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
        val passphrase: ByteArray = SQLiteDatabase.getBytes("com.catnip.pixagram".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            FeedsDatabase::class.java, "Feeds.db"
        ).openHelperFactory(factory).fallbackToDestructiveMigration().build()
    }
}
val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(
                CertificatePinner.Builder()
                    .add(
                        BuildConfig.BASE_URL_PINNER,
                        "sha256/hS5jJ4P+iQUErBkvoWBQOd1T7VOAYlOVegvv1iMzpxA="
                    )
                    .add(
                        BuildConfig.BASE_URL_PINNER,
                        "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4="
                    )
                    .build()
            )
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
    single { PrefDataSource(get()) }
    factory { AppExecutors() }
    single<IFeedsRepository> {
        FeedsRepository(
            get(),
            get(),
            get(),
            get()
        )
    }
}