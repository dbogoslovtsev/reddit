package com.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

typealias CompletionBlock<T> = BaseViewModel.Request<T>.() -> Unit

abstract class BaseViewModel : ViewModel() {

    val errorLiveData = MutableLiveData<Throwable>()


    abstract fun init(savedStateHandle: SavedStateHandle)

    protected open fun handleError(throwable: Throwable) {
        errorLiveData.postValue(throwable)
    }

    protected fun <T> execute(
        call: suspend CoroutineScope.() -> T,
        completionBlock: CompletionBlock<T>?
    ): Job {
        return viewModelScope.launch {
            val callback = completionBlock?.let { Request<T>().apply(it) }
            try {
                val result = call()
                callback?.onComplete?.invoke(result)
            } catch (throwable: Throwable) {
                callback?.onError?.invoke(throwable) ?: handleError(throwable)
            }
        }
    }

    class Request<T> {
        var onComplete: ((T) -> Unit)? = null
        var onError: ((Throwable) -> Unit)? = null
    }
}
