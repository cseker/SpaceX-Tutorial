package com.cihadseker.spacex.data.repository

import com.cihadseker.spacex.data.repository.reqres.RocketAllListItem
import retrofit2.http.GET

interface SpaceXService {

    @GET("rockets")
    suspend fun getRocketList(): List<RocketAllListItem>
}
