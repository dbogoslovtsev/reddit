package com.domain.executor


import io.reactivex.Scheduler

interface Executor {
    val scheduler: Scheduler
}
