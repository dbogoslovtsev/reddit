package com.reddit.di.executor


import com.domain.executor.WorkExecutionThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class IOWorkThread : WorkExecutionThread {

    override val scheduler: Scheduler
        get() = Schedulers.io()
}
