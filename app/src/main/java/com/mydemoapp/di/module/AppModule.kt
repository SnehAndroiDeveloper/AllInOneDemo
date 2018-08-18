package com.mydemoapp.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.mydemoapp.common.utils.Constants
import com.mydemoapp.data.database.AppDatabase
import com.mydemoapp.data.database.repository.votes.VotesRepo
import com.mydemoapp.data.database.repository.votes.VotesRepository
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun provideAppDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, Constants.APP_DB_NAME).build()

//    @Provides
//    @ApiKeyInfo
//    internal fun provideApiKey(): String = BuildConfig.API_KEY
//
//    @Provides
//    @PreferenceInfo
//    internal fun provideprefFileName(): String = AppConstants.PREF_NAME
//
//    @Provides
//    @Singleton
//    internal fun providePrefHelper(appPreferenceHelper: AppPreferenceHelper): PreferenceHelper = appPreferenceHelper
//
//    @Provides
//    @Singleton
//    internal fun provideProtectedApiHeader(@ApiKeyInfo apiKey: String, preferenceHelper: PreferenceHelper)
//            : ApiHeader.ProtectedApiHeader = ApiHeader.ProtectedApiHeader(apiKey = apiKey,
//            userId = preferenceHelper.getCurrentUserId(),
//            accessToken = preferenceHelper.getAccessToken())
//
//    @Provides
//    @Singleton
//    internal fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper
//
//    @Provides
//    @Singleton
//    internal fun provideQuestionRepoHelper(appDatabase: AppDatabase): QuestionRepo = QuestionRepository(appDatabase.questionsDao())

    @Provides
    @Singleton
    internal fun provideOptionsRepoHelper(appDatabase: AppDatabase): VotesRepo = VotesRepository(appDatabase.getVotesDao())

    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
//
//    @Provides
//    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()
}