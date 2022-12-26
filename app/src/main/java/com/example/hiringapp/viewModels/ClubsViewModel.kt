package com.example.hiringapp.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hiringapp.data.Repository
import com.example.hiringapp.data.local.LocalClubItem
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

private const val TAG = "ClubsViewModel"

@HiltViewModel

class ClubsViewModel @Inject constructor(
    private val clubRepository: Repository
) :
    ViewModel() {


    private var remoteclubsList = MutableLiveData<List<LocalClubItem>>()
    private var localClubList = MutableLiveData<List<LocalClubItem>>()

    //get data from remote
    fun getRemoteClubListObserver(): MutableLiveData<List<LocalClubItem>> {
        return remoteclubsList


    }
    //get All data from Room
    fun getLocalClubItem(): MutableLiveData<List<LocalClubItem>> {
        return localClubList
    }
    //get data  directly from Retrofit Api
    fun getDataFromServer(context: Context) {
        clubRepository.makeApiCall().subscribe(getClubListObserverRx(context))
    }

    //get data sorted by name from Room
    fun getClubsByName(context: Context) {
        clubRepository.getClubsByName(context)?.subscribe(getClubLocalListByNameObserverRx())

    }
    //get data sorted by value from Room
    fun getClubsByValue(context: Context) {
        clubRepository.getClubsByValue(context)?.subscribe(getClubLocalListByValueObserverRx())
    }

    // OBSERVERS
    private fun getClubListObserverRx(context: Context): Observer<List<LocalClubItem>> {
        return object : Observer<List<LocalClubItem>> {

            override fun onNext(t: List<LocalClubItem>) {
                Log.d("ViewModel", "onNext: $t")

                remoteclubsList.postValue(t)
                clubRepository.insertDataToRoom(t, context)

            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: $e")
            }

            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

                Log.d("ViewModel", "onSubscribe: subscribed ")
            }

        }
    }

    private fun getClubLocalListByNameObserverRx(): Observer<List<LocalClubItem>> {
        return object : Observer<List<LocalClubItem>> {

            override fun onNext(t: List<LocalClubItem>) {
                Log.d("LocalData", "LocalData $t")
                localClubList.postValue(t)

            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: $e")
            }

            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

                Log.d("ViewModel", "onSubscribe: subscribed ")
            }

        }
    }

    private fun getClubLocalListByValueObserverRx(): Observer<List<LocalClubItem>> {
        return object : Observer<List<LocalClubItem>> {

            override fun onNext(t: List<LocalClubItem>) {
                Log.d("LocalData", "LocalData $t")
                localClubList.postValue(t)

            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: $e")
            }

            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

                Log.d("ViewModel", "onSubscribe: subscribed ")
            }

        }
    }


}