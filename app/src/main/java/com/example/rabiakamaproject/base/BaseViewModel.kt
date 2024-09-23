package com.example.rabiakamaproject.base


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    protected val mProgress by lazy { MutableLiveData<Boolean>() }
    val progress: LiveData<Boolean> by lazy { mProgress }
    open fun init() {
        // Triggered when initializing a view model
    }

    fun updateProgress(inProgress: Boolean) = mProgress.postValue(inProgress)
}