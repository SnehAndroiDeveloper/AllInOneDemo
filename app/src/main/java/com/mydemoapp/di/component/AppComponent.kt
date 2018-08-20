package com.mydemoapp.di.component

import com.mydemoapp.di.module.*
import com.mydemoapp.di.qualifier.AppScope
import com.mydemoapp.network.GifApi
import com.mydemoapp.network.RxScheduler
import dagger.Component


@AppScope
@Component(modules = [(NetworkModule::class), (AppContextModule::class), (RxModule::class), (GifApiServiceModule::class), (RoomModule::class)])
interface AppComponent {
    fun rxSchedulers(): RxScheduler
    fun apiService(): GifApi
}