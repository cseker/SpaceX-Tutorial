package com.cihadseker.spacex.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rocket_info")
data class RocketInfo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "rocketName")
    val rocketName: String,
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "company")
    val company: String,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean,
    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,
    @ColumnInfo(name = "description")
    val description: String
)