package com.cihadseker.core.extension

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.annotation.MainThread
import androidx.fragment.app.viewModels
import com.cihadseker.core.base.BaseFragment
import com.cihadseker.core.base.BaseViewModel
import com.cihadseker.core.extension.datalistener.bindingListener
import com.cihadseker.core.extension.datalistener.viewListener

fun Activity.hideKeyboard() {
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

@MainThread
inline fun <reified VM : BaseViewModel> BaseFragment<*>.injectVM(): Lazy<VM> {
    return viewModels<VM>().also {
        it.viewListener(this)
        it.bindingListener(this)
    }
}