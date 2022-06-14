package com.cihadseker.spacex.ui.splash

import com.cihadseker.core.base.BaseViewModel
import com.cihadseker.core.extension.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class FRSplashVM @Inject constructor() : BaseViewModel() {

    var navigateToHome = MutableSharedFlow<Unit>()

    companion object {
        var splashDisplayDuration = 2500L
    }

    fun initVM() = launch {
        delay(splashDisplayDuration)
        nextNavigate()
    }

    private fun nextNavigate() = launch {
        navigateToHome.emit(Unit)
    }
}