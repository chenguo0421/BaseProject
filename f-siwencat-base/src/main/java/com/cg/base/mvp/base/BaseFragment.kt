package com.cg.base.mvp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cg.base.mvp.base.intf.BaseView
import com.trello.rxlifecycle4.components.support.RxFragment

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/27 20:41
 */
abstract class BaseFragment<V : BaseView, P : BasePresenter<V>> : RxFragment(){
    private var mView: V? = null
    private var mPresenter: P? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (mPresenter == null) {
            mPresenter = createPresenter()
        }

        if (mView == null) {
            mView = createView()
        }

        mPresenter!!.attachView(mView!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(initLayoutId(), container, false)
        initViews(v)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initListener()
    }


    protected abstract fun createPresenter(): P
    protected abstract fun createView(): V
    protected abstract fun initViews(view: View?)
    protected abstract fun initLayoutId(): Int
    protected abstract fun initData()
    protected abstract fun initListener()
    abstract fun setBundleExtra(bundle: Bundle?)


    override fun onDestroy() {
        mPresenter?.detachView()
        super.onDestroy()
    }
}