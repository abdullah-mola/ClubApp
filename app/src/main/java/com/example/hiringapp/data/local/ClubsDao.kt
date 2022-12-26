package com.example.hiringapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Observable

//Data Access Object interface with get / insert /filter Queries
@Dao
interface ClubsDao {

    @Query("SELECT * FROM clubs")
    fun getAllClubs(): Observable<List<LocalClubItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllClubs(clubList: List<LocalClubItem>)

    @Query("SELECT * FROM clubs ORDER BY name ASC")
    fun getAllClubsByName(): Observable<List<LocalClubItem>>

    @Query("SELECT * FROM clubs ORDER BY value DESC")
    fun getAllClubsByValue(): Observable<List<LocalClubItem>>

}