package com.example.hiringapp.data.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDataBase {
        return Room.databaseBuilder(appContext, AppDataBase::class.java, "data_club_base")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChannelDao(appDatabase: AppDataBase):ClubsDao{
        return appDatabase.clubsDao()
    }
}