package com.mydemoapp.data.database.repository.gif

import com.mydemoapp.data.database.repository.votes.Votes
import io.reactivex.Observable
import io.reactivex.Single

interface GifRepo {

    fun isGifRepoEmpty(): Observable<Boolean>

    fun insertGifs(arrGifs: List<Gif>): Observable<Boolean>

    fun loadGif(): List<Gif>

    fun deleteGif()
}