package com.mydemoapp.di.module

import com.mydemoapp.network.AppRxSchedulers
import com.mydemoapp.network.RxScheduler
import dagger.Module
import dagger.Provides


@Module
class RxModule {
    @Provides
    internal fun provideRxSchedulers(): RxScheduler {
        return AppRxSchedulers()
    }
}