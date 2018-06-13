package com.raqun.peanut

import java.util.concurrent.Executor


/**
 * Created by tyln on 13.06.2018.
 */
abstract class Peanut<T> {
    private var worker: Executor
    private var receiver: Executor
    private var subscriber: Subscriber<T>? = null

    init {
        worker = DefaultExecutorSupplier.provideMainExecutor()
        receiver = DefaultExecutorSupplier.provideMainExecutor()
    }

    abstract fun call()

    fun runOn(worker: Executor?): Peanut<T> {
        if (worker != null) {
            this.worker = worker
        }
        return this
    }

    fun receiveOn(receiver: Executor?): Peanut<T> {
        if (receiver != null) {
            this.receiver = receiver
        }
        return this
    }

    fun run(subscriber: Subscriber<T>) {
        this.subscriber = subscriber
        worker.execute { call() }
    }

    fun run() {
        worker.execute { call() }
    }

    fun onSuccess(data: T) {
        if (subscriber == null) {
            return
        }
        receiver.execute { subscriber?.onComplete(data) }
    }

    fun onFail(e: Error) {
        if (subscriber == null) {
            return
        }
        receiver.execute { subscriber?.onError(e) }
    }

    fun destroy() {
        subscriber = null
    }

    interface Subscriber<T> {
        fun onComplete(data: T)

        fun onError(e: Error)
    }
}