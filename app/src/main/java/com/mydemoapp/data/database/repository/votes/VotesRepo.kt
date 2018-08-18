package com.mydemoapp.data.database.repository.votes

import io.reactivex.Observable
import io.reactivex.Single

interface VotesRepo {

    fun isVotesRepoEmpty(): Observable<Boolean>

    fun insertVotes(arrVotes: List<Votes>): Observable<Boolean>

    fun loadVotes(): List<Votes>
}