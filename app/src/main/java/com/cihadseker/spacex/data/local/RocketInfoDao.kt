package com.cihadseker.spacex.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RocketInfoDao {

    @Update
    fun updateRocket(rocketInfo: RocketInfo)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAllRocket(rocketInfo: List<RocketInfo>)

    @Query("SELECT * FROM rocket_info")
    fun readAllRocketData(): Flow<List<RocketInfo>>

    @Query("SELECT * FROM rocket_info WHERE isFavorite = 1")
    fun readAllFavorite(): Flow<List<RocketInfo>>

    @Query("DELETE FROM rocket_info")
    suspend fun deleteAllRocket()
}