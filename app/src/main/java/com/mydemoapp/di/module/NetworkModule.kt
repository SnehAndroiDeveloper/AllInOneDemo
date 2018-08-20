package com.mydemoapp.di.module

import android.content.Context
import com.mydemoapp.di.qualifier.AppScope
import retrofit2.converter.gson.GsonConverterFactory
import dagger.Provides
import com.mydemoapp.network.AppRxSchedulers
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import okhttp3.logging.HttpLoggingInterceptor
import dagger.Module
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File


@Module
class NetworkModule {

    @AppScope
    @Provides
    internal fun provideHttpClient(logger: HttpLoggingInterceptor, cache: Cache): OkHttpClient {

        val builder = OkHttpClient().newBuilder()
        builder.addInterceptor(logger)
        builder.cache(cache)
        return builder.build()
    }

    @AppScope
    @Provides
    internal fun provideInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @AppScope
    @Provides
    internal fun provideCache(file: File): Cache {
        return Cache(file, 10 * 10 * 1000)
    }

    @AppScope
    @Provides
    internal fun provideCacheFile(context: Context): File {
        return context.getFilesDir()
    }

    @AppScope
    @Provides
    internal fun provideRxAdapter(): RxJavaCallAdapterFactory {
        return RxJavaCallAdapterFactory.createWithScheduler(AppRxSchedulers.INTERNET_SCHEDULERS)
    }


    @Provides
    internal fun provideGsonClient(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

}