package com.shark.radio.injection.executor


import com.shark.radio.usecase.executor.WorkExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class IOWorkThread : WorkExecutionThread {

    override val scheduler: Scheduler
        get() = Schedulers.io()
}
