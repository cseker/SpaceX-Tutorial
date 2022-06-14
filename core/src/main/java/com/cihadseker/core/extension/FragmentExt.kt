package com.cihadseker.core.extension

import androidx.fragment.app.Fragment
import com.cihadseker.core.extension.hideKeyboard

fun Fragment.clearCurrentFocus() {
    requireActivity().currentFocus?.clearFocus()
    requireActivity().hideKeyboard()
}