package com.mydemoapp.ui.gif.core

import android.util.Log
import com.mydemoapp.MyDemoApp
import com.mydemoapp.data.database.repository.gif.Gif
import com.mydemoapp.data.database.repository.votes.Votes
import com.mydemoapp.network.RxScheduler
import rx.Subscription
import rx.subscriptions.CompositeSubscription


class GifPresenter(
        private val rxSchedulers: RxScheduler,
        private val model: GifModel,
        private val view: GifView,
        private val subscriptions: CompositeSubscription
) {
    private var arrGif: ArrayList<Gif> = ArrayList()

    private fun gifList(text: String): Subscription {
        return model.isNetworkAvailable.doOnNext { networkAvailable ->
            if (!networkAvailable) {
            }
        }.flatMap { model.provideListGifs(text) }
                .subscribeOn(rxSchedulers.internet())
                .observeOn(rxSchedulers.androidThread())
                .subscribe({ heroes ->
                    val arrGif = ArrayList<Gif>()

                    for (i in heroes.data) {
                        val gif = Gif()
                        gif.gifURL = i.images.previewGif.url
                        gif.itemId = i.id
                        gif.videoURL = i.images.original.mp4

                        arrGif.add(gif)
                    }

                    view.swapAdapter(arrGif)
                    this.arrGif = arrGif

                    addDataInDatabase()

                }, { throwable ->
                    Log.e("TAG", throwable.message)
                }
                )
    }

    private fun addDataInDatabase() {
        MyDemoApp.getDatabase().getGifDao().deleteTable()
        MyDemoApp.getDatabase().getGifDao().insertAll(arrGif)
    }

    fun searchClick(text: String) {
        if (!text.isEmpty()) {
            subscriptions.add(gifList(text))
            subscriptions.add(respondToClick())
        }
    }

    fun onDestroy() {
        val arrVotes = ArrayList<Votes>()
        for (i in arrGif) {
            val votes = Votes()
            votes.upCount = i.upCount
            votes.downCount = i.downCount
            votes.itemId = i.itemId

            arrVotes.add(votes)
        }
        MyDemoApp.getDatabase().getVotesDao().insertAll(arrVotes)
        subscriptions.clear()
    }

    fun onCreate() {
        this.arrGif = MyDemoApp.getDatabase().getGifDao().loadAllGifs() as ArrayList<Gif>
        view.swapAdapter(arrGif)
    }

    private fun respondToClick(): Subscription {
        return view.itemClicks().subscribe { position: Int -> model.gotoVideoActivity(position, arrGif) }
    }
}