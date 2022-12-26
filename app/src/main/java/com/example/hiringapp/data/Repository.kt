package com.example.hiringapp.data

import android.content.Context
import com.example.hiringapp.data.local.ClubsDao
import com.example.hiringapp.data.local.LocalClubItem
import com.example.hiringapp.data.local.getDatabase
import com.example.hiringapp.data.remote.ApiService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class Repository @Inject constructor(
    private val api: ApiService,
    private val locald: ClubsDao
) {
    fun makeApiCall(): Observable<List<LocalClubItem>> {
        return api.getAllClubs().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }
    fun getClubsByName(context: Context):Observable<List<LocalClubItem>>?{
        return getDatabase(context)?.clubsDao()?.getAllClubsByName()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun getClubsByValue(context: Context):Observable<List<LocalClubItem>>?{
        return  getDatabase(context)?.clubsDao()?.getAllClubsByValue()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
    }

    fun insertDataToRoom(clubList:List<LocalClubItem>,context:Context){
        getDatabase(context)?.clubsDao()?.insertAllClubs(clubList)
    }

}