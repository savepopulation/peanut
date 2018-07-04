package com.raqun.peanut

import com.raqun.peanut.util.Util
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by tyln on 13.06.2018.
 */
object DefaultExecutorSupplier : ExecutorSupplier {
    private val MAX_IO_THREAD_COUNT = calculateMaxIThreadCount()

    // Executors
    private val mainThreadExecutor: Executor = MainThreadExecutor()
    private val ioExecutor: Executor = Executors.newFixedThreadPool(MAX_IO_THREAD_COUNT)

    override fun provideMainExecutor(): Executor = mainThreadExecutor

    override fun provideIoExecutor(): Executor = ioExecutor

    private fun calculateMaxIThreadCount(): Int {
        val calculatedIoThreadCount = Util.getNumberOfCores()
        return if (calculatedIoThreadCount < 2) 2 else calculatedIoThreadCount
    }
}