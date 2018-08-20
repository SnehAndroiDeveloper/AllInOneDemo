package com.mydemoapp.data.database.repository.votes

import io.reactivex.Observable
import javax.inject.Inject

class VotesRepository @Inject constructor(private val votesDao: VotesDao) : VotesRepo {

    override fun isVotesRepoEmpty(): Observable<Boolean> = Observable.fromCallable { votesDao.loadAllVotes().isEmpty() }

    override fun insertVotes(arrVotes: List<Votes>): Observable<Boolean> {
        votesDao.insertAll(arrVotes)
        return Observable.just(true)
    }

    override fun loadVotes(): List<Votes> {
        return votesDao.loadAllVotes()
    }
}