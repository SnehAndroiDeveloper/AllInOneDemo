package com.mydemoapp.di.module

import android.content.Context
import com.mydemoapp.di.qualifier.AppScope
import dagger.Module
import dagger.Provides


@Module
class AppContextModule(private val context: Context) {

    @AppScope
    @Provides
    fun provideAppContext(): Context {
        return context
    }

}