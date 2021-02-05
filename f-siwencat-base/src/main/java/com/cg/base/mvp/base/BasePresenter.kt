package com.cg.base.mvp.base

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/11/25 15:05
 */
abstract class BasePresenter<V> {
    protected val TAG = this.javaClass.name
    private var mView: V? = null

    open fun getView(): V? {
        return mView
    }

    open fun attachView(view: V) {
        mView = view
    }

    open fun detachView() {
        mView = null
        onDestroy()
    }

    open fun onDestroy() {}
}