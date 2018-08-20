package com.mydemoapp.di.module

import android.arch.persistence.room.Room
import dagger.Provides
import com.mydemoapp.data.database.AppDatabase
import com.mydemoapp.data.database.repository.gif.GifDao
import com.mydemoapp.data.database.repository.gif.GifRepo
import com.mydemoapp.data.database.repository.gif.GifRepository
import com.mydemoapp.data.database.repository.votes.VotesDao
import com.mydemoapp.data.database.repository.votes.VotesRepo
import com.mydemoapp.data.database.repository.votes.VotesRepository
import com.mydemoapp.di.qualifier.AppScope
import dagger.Module


@Module
class RoomModule {

    @AppScope
    @Provides
    fun providesVotesDao(demoDatabase: AppDatabase): VotesDao {
        return demoDatabase.getVotesDao()
    }

    @AppScope
    @Provides
    fun votesRepo(voteDao: VotesDao): VotesRepo {
        return VotesRepository(voteDao)
    }

    @AppScope
    @Provides
    fun providesGifDao(demoDatabase: AppDatabase): GifDao {
        return demoDatabase.getGifDao()
    }

    @AppScope
    @Provides
    fun gifRepo(gifDao: GifDao): GifRepo {
        return GifRepository(gifDao)
    }

}