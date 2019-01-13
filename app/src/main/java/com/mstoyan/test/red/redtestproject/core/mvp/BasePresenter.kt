package com.mstoyan.test.red.redtestproject.core.mvp

abstract class BasePresenter<T>: BasePresenterInterface<T> {

    protected var view : T? = null

    override fun onViewActive(view: T) {
        this.view = view
    }

    override fun onViewInactive() {
        view = null
    }
}