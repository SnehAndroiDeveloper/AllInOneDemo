package com.mydemoapp.data.database.repository.votes

import android.arch.persistence.room.*

@Dao
interface VotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(arrVotes: List<Votes>)

    @Query("SELECT * FROM votes")
    fun loadAllVotes(): List<Votes>
}