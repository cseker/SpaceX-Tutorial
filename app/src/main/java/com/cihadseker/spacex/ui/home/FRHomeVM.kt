package com.cihadseker.spacex.ui.home

import androidx.lifecycle.viewModelScope
import com.cihadseker.core.base.BaseViewModel
import com.cihadseker.core.extension.launch
import com.cihadseker.spacex.data.domain.SpaceXListUseCase
import com.cihadseker.spacex.data.local.RocketInfo
import com.cihadseker.spacex.data.local.RocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FRHomeVM @Inject constructor(
    private val useCase: SpaceXListUseCase,
    private val repository: RocketRepository
) : BaseViewModel() {

    val rocketList: StateFlow<List<RocketInfo>?>
        get() = repository.allList().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), null)

    fun getRocketList() = launch {
        useCase(Unit)
            .onStart { showLoading() }
            .onCompletion { hideLoading() }
            .collect {}
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun updateRocketItem(item: RocketInfo) {
        GlobalScope.launch {
            item.copy(isFavorite = !item.isFavorite).let {
                repository.updateRocket(it)
            }
        }
    }
}