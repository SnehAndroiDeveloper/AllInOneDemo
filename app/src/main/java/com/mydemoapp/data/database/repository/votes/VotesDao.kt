package com.mydemoapp.data.database.repository.votes

import android.arch.persistence.room.*

@Dao
interface VotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(arrVotes: List<Votes>)

    @Query("SELECT * FROM votes")
    fun loadAllVotes(): List<Votes>

    @Query("SELECT up_vote FROM votes WHERE item_id =:itemId")
    fun getUpCount(itemId: String): Int

    @Query("SELECT down_vote FROM votes WHERE item_id =:itemId")
    fun getDownCount(itemId: String): Int
}