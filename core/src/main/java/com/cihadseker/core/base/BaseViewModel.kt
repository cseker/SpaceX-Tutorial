package com.cihadseker.core.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.cihadseker.core.extension.launch
import com.cihadseker.core.model.ServiceError
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow


abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    val error = MutableSharedFlow<ServiceError>()
    val showProgress = MutableSharedFlow<Unit>()
    val hideProgress = MutableSharedFlow<Unit>()
    val goBack = MutableSharedFlow<Unit>()
    val finish = MutableSharedFlow<Unit>()

    fun showLoading() = launch {
        showProgress.emit(Unit)
    }

    fun hideLoading() = launch {
        hideProgress.emit(Unit)
    }

    fun finish() = launch {
        finish.emit(Unit)
    }

    fun goBack() = launch {
        goBack.emit(Unit)
    }

    fun showError(e: ServiceError) = launch {
        error.emit(e)
    }

    private fun getError(t: Throwable) = when (t) {
        else -> ServiceError.NetworkError
    }

    val exceptionHandler = CoroutineExceptionHandler { _, e ->
        val error = getError(e)
        showError(error)
    }
}