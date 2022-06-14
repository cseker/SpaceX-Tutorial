package com.cihadseker.spacex.ui

import androidx.lifecycle.SavedStateHandle
import com.cihadseker.core.base.BaseViewModel
import com.cihadseker.spacex.data.local.RocketInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class SharedRocketVM @Inject constructor(
    val handle: SavedStateHandle
) : BaseViewModel() {

    var selectedRocketUI: RocketInfo? = null
}
