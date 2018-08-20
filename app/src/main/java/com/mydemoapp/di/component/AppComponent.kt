package com.mydemoapp.di.component

import com.mydemoapp.di.module.AppContextModule
import com.mydemoapp.di.module.GifApiServiceModule
import com.mydemoapp.di.module.NetworkModule
import com.mydemoapp.di.module.RxModule
import com.mydemoapp.di.qualifier.AppScope
import com.mydemoapp.network.GifApi
import com.mydemoapp.network.RxScheduler
import dagger.Component


@AppScope
@Component(modules = [(NetworkModule::class), (AppContextModule::class), (RxModule::class), (GifApiServiceModule::class)])
interface AppComponent {
    fun rxSchedulers(): RxScheduler
    fun apiService(): GifApi
}