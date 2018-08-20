package com.mydemoapp.data.database.repository.gif

import com.mydemoapp.data.database.repository.votes.Votes
import com.mydemoapp.data.database.repository.votes.VotesRepo
import io.reactivex.Observable
import javax.inject.Inject

class GifRepository @Inject constructor(private val gifDao: GifDao) : GifRepo {
    override fun isGifRepoEmpty(): Observable<Boolean> = Observable.fromCallable { gifDao.loadAllGifs().isEmpty() }

    override fun insertGifs(arrGifs: List<Gif>): Observable<Boolean> {
        gifDao.insertAll(arrGifs)
        return Observable.just(true)
    }

    override fun loadGif(): List<Gif> {
        return gifDao.loadAllGifs()
    }

    override fun deleteGif() {
        gifDao.deleteTable()
    }
}