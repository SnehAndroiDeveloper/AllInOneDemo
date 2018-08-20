package com.mydemoapp.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.mydemoapp.data.database.repository.gif.Gif
import com.mydemoapp.data.database.repository.gif.GifDao
import com.mydemoapp.data.database.repository.votes.Votes
import com.mydemoapp.data.database.repository.votes.VotesDao


@Database(
        entities = [Votes::class, Gif::class],
        version = 1,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getVotesDao(): VotesDao

    abstract fun getGifDao(): GifDao
}