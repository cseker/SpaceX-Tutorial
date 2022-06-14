package com.cihadseker.core.extension

fun Boolean?.isTrue() = this == true

infix fun Int.with(x: Int) = this.or(x)