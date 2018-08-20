package com.mydemoapp.data.database.repository.gif

import android.arch.persistence.room.*

@Dao
interface GifDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(arrVotes: List<Gif>)

    @Query("SELECT * FROM gif")
    fun loadAllGifs(): List<Gif>

    @Query("DELETE FROM gif")
    fun deleteTable()
}