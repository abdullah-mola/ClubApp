package com.example.hiringapp.data.remote

import com.example.hiringapp.data.local.LocalClubItem
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
// Retrofit Api Service interface with get call
interface ApiService {
    @GET("clubs.json")
    fun getAllClubs(): Observable<List<LocalClubItem>>

}