package com.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    abstract fun init(savedStateHandle: SavedStateHandle)
    val errorLiveData = MutableLiveData<String>()
}