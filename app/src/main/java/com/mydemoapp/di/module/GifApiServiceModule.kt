package com.mydemoapp.di.module

import com.mydemoapp.di.qualifier.AppScope
import com.mydemoapp.network.GifApi
import com.mydemoapp.network.WSConstant
import dagger.Module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import dagger.Provides


@Module
class GifApiServiceModule {
    @AppScope
    @Provides
    fun provideApiService(client: OkHttpClient, gson: GsonConverterFactory, rxAdapter: RxJavaCallAdapterFactory): GifApi {
        val retrofit = Retrofit.Builder().client(client)
                .baseUrl(WSConstant.WS_BASE_URL).addConverterFactory(gson).addCallAdapterFactory(rxAdapter).build()

        return retrofit.create<GifApi>(GifApi::class.java)
    }
}