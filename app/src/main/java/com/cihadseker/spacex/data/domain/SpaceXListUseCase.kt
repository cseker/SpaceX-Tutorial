package com.cihadseker.spacex.data.domain

import com.cihadseker.core.base.BaseUseCase
import com.cihadseker.core.util.Mapper
import com.cihadseker.spacex.data.local.RocketInfo
import com.cihadseker.spacex.data.local.RocketRepository
import com.cihadseker.spacex.data.repository.SpaceXRepository
import com.cihadseker.spacex.data.repository.reqres.RocketAllListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SpaceXListUseCase @Inject constructor(
    private val repo: SpaceXRepository,
    private val dataRepo: RocketRepository
) : BaseUseCase<Unit, Unit>() {

    override fun execute(params: Unit): Flow<Unit> =
        repo.getRocketList().map(::response2UI).map {
            dataRepo.addAll(it)
        }

    private fun response2UI(response: List<RocketAllListItem>): List<RocketInfo> {
        return object : Mapper<RocketAllListItem, RocketInfo>() {
            override fun map(value: RocketAllListItem): RocketInfo {
                with(value) {
                    return RocketInfo(
                        id = id ?: "",
                        rocketName = name ?: "",
                        country = country ?: "",
                        company = company ?: "",
                        isFavorite = false,
                        imageUrl = flickr_images?.get(0) ?: "",
                        description = description ?: ""
                    )
                }
            }
        }.map(response)
    }
}