package com.cihadseker.spacex.ui.favorite

import androidx.lifecycle.viewModelScope
import com.cihadseker.core.base.BaseViewModel
import com.cihadseker.spacex.data.local.RocketInfo
import com.cihadseker.spacex.data.local.RocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FRFavoriteVM @Inject constructor(
    private val repository: RocketRepository
) : BaseViewModel() {

    val allList: StateFlow<List<RocketInfo>>
        get() = repository.readFavorites().stateIn(viewModelScope, SharingStarted.WhileSubscribed(), listOf())

    @OptIn(DelicateCoroutinesApi::class)
    fun updateRocketItem(item: RocketInfo) {
        GlobalScope.launch {
            item.copy(isFavorite = !item.isFavorite).let {
                repository.updateRocket(it)
            }
        }
    }
}