package com.shark.radio.usecase.executor


import io.reactivex.Scheduler

interface Executor {
    val scheduler: Scheduler
}
