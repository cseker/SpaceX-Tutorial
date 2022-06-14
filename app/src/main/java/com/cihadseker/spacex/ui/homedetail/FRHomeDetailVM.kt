package com.cihadseker.spacex.ui.homedetail

import com.cihadseker.core.base.BaseViewModel
import com.cihadseker.core.extension.launch
import com.cihadseker.spacex.data.local.RocketInfo
import com.cihadseker.spacex.data.local.RocketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FRHomeDetailVM @Inject constructor(private val dataRepository: RocketRepository) : BaseViewModel() {

    val item = MutableStateFlow<RocketInfo?>(null)

    fun setValue(selectedRocketUI: RocketInfo?) = launch {
        item.emit(selectedRocketUI)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun updateItem(item: RocketInfo?) {
        GlobalScope.launch {
            item?.copy(isFavorite = !item.isFavorite)?.let {
                setValue(it)
                dataRepository.updateRocket(it)
            }
        }
    }
}