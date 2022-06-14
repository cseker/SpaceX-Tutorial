package com.cihadseker.spacex.data.repository

import com.cihadseker.core.base.BaseRepository
import com.cihadseker.spacex.data.repository.reqres.RocketAllListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

interface SpaceXRepository {

    fun getRocketList(): Flow<List<RocketAllListItem>>
}

class SpaceXRepositoryImpl @Inject constructor(
    private val service: SpaceXService,
) : BaseRepository(), SpaceXRepository {

    override fun getRocketList() = sendRequest {
        service.getRocketList()
    }.catch { t->
        t.message
    }
}
