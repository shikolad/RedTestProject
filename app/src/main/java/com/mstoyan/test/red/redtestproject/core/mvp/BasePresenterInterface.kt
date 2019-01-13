package com.mstoyan.test.red.redtestproject.core.mvp

interface BasePresenterInterface<T> {

    fun onViewActive(view: T)
    fun onViewInactive()
}