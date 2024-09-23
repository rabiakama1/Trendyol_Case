package com.example.rabiakamaproject.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    /** Creates ViewModel object which is [T] */
    abstract fun initViewModel(): T

    /** Layout resource int */
    @LayoutRes
    abstract fun getLayoutRes(): Int

    /** ViewModel object for Activity that extends [BaseActivity] */
    lateinit var viewModel: T

    //region Public Method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutRes())
        setViewModel()
    }

    private fun setViewModel() {
        viewModel = initViewModel()
    }

    //endregion Private Method
}