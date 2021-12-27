package com.reddit.di.executor


import com.shark.radio.usecase.executor.UIExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : UIExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}
