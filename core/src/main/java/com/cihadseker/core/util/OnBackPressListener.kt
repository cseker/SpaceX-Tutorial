package com.cihadseker.core.util

interface OnBackPressListener {

    fun isBackEnable():Boolean

    fun onBackPressed():Boolean
}