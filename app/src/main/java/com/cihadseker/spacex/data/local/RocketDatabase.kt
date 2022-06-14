package com.cihadseker.spacex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RocketInfo::class], version = 1, exportSchema = false)
abstract class RocketDatabase : RoomDatabase() {

    abstract fun rocketDao(): RocketInfoDao

}