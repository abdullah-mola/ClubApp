package com.example.hiringapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
//model data class used for retrofit and room
@Entity(tableName = "clubs")
data class LocalClubItem(
    @PrimaryKey
    val id: String,
    val name: String,
    val country: String,
    val value: Int,
    val image: String,
    val european_titles: Int

)
