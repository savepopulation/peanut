package com.raqun.peanut

import java.util.concurrent.Executor

/**
 * Created by tyln on 13.06.2018.
 */
interface ExecutorSupplier {
    fun provideIoExecutor(): Executor

    fun provideMainExecutor(): Executor
}