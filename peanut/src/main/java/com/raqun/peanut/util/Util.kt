package com.raqun.peanut.util

import android.os.Build
import com.raqun.peanut.BuildConfig

/**
 * Created by tyln on 15.06.2018.
 */
class Util {
    companion object {
        fun getNumberOfCores() = Runtime.getRuntime().availableProcessors()
    }
}