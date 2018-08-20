package com.mydemoapp.ui.gif.core

import android.util.Log
import com.mydemoapp.data.model.GifDataModel
import com.mydemoapp.network.RxScheduler
import rx.Subscription
import rx.subscriptions.CompositeSubscription


class GifPresenter(
        private val rxSchedulers: RxScheduler,
        private val model: GifModel,
        private val view: GifView,
        private val subscriptions: CompositeSubscription
) {
    var arrGifDataModel: ArrayList<GifDataModel> = ArrayList()

    private fun gifList(): Subscription {
        return model.isNetworkAvailable.doOnNext { networkAvailable ->
            if (!networkAvailable) {
            }
        }.flatMap { model.provideListGifs() }
                .subscribeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread())
                .subscribe({ heroes ->
                    view.swapAdapter(heroes.data)
                    arrGifDataModel = heroes.data
                }, { throwable ->

                    //                    TODO: check
                    Log.e("TAG", throwable.message)
                    /*UiUtils.handleThrowable(throwable)*/
                }
                )
    }

    fun onCreate() {
        subscriptions.add(gifList())
        subscriptions.add(respondToClick())
    }

    fun onDestroy() {
        subscriptions.clear()
    }

    private fun respondToClick(): Subscription {
        return view.itemClicks().subscribe { integer: Int -> model.gotoVideoActivity(arrGifDataModel[integer]) }
    }
}