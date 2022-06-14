package com.cihadseker.spacex.data.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RocketRepository @Inject constructor(private val rocketInfoDao: RocketInfoDao) {

    fun allList(): Flow<List<RocketInfo>> {
        return rocketInfoDao.readAllRocketData()
    }

    suspend fun addAll(rocketInfo: List<RocketInfo>) {
        rocketInfoDao.addAllRocket(rocketInfo)
    }

    fun readFavorites(): Flow<List<RocketInfo>> {
        return rocketInfoDao.readAllFavorite()
    }

    fun updateRocket(rocketInfo: RocketInfo) {
        rocketInfoDao.updateRocket(rocketInfo)
    }

    suspend fun deleteAllRocket() {
        rocketInfoDao.deleteAllRocket()
    }
}