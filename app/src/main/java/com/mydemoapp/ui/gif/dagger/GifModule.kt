package com.mydemoapp.ui.gif.dagger

import com.mydemoapp.network.GifApi
import com.mydemoapp.network.RxScheduler
import com.mydemoapp.ui.gif.MainActivity
import com.mydemoapp.ui.gif.core.GifModel
import com.mydemoapp.ui.gif.core.GifPresenter
import com.mydemoapp.ui.gif.core.GifView
import dagger.Module
import dagger.Provides
import rx.subscriptions.CompositeSubscription


@Module
class GifModule(private val context: MainActivity) {
    @GifScope
    @Provides
    fun provideView(): GifView {
        return GifView(context)
    }

    @GifScope
    @Provides
    fun providePresenter(schedulers: RxScheduler, view: GifView, model: GifModel): GifPresenter {
        val subscriptions = CompositeSubscription()
        return GifPresenter(schedulers, model, view, subscriptions)
    }


    @GifScope
    @Provides
    fun provideContext(): MainActivity {
        return context
    }

    @GifScope
    @Provides
    fun provideModel(api: GifApi): GifModel {
        return GifModel(context, api)
    }
}