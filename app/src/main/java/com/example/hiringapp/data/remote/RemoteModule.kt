package com.example.hiringapp.data.remote

import com.example.hiringapp.data.Repository
import com.example.hiringapp.data.local.ClubsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {


    @Singleton
    @Provides
    fun clubsApiRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://public.allaboutapps.at/hiring/")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun provideClubsApi( retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesClubsRepository(api: ApiService, locald: ClubsDao): Repository {
        return Repository(api,locald)
    }
}