package com.raqun.peanut

import java.util.concurrent.Executor

/**
 * Created by tyln on 13.06.2018.
 */
object Schedulers {
    fun io(): Executor = DefaultExecutorSupplier.provideIoExecutor()

    fun main(): Executor = DefaultExecutorSupplier.provideMainExecutor()
}