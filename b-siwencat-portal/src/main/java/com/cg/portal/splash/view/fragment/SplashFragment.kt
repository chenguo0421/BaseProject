package com.cg.portal.splash.view.fragment

import android.os.Bundle
import android.content.Context
import android.view.View
import android.view.Gravity
import android.view.ViewGroup
import com.cg.base.mvp.base.BaseDialogFragment
import com.cg.portal.R
import com.cg.portal.splash.contract.SplashFMContract
import com.cg.portal.splash.presenter.SplashFMPresenter

/**
 *  author : ChenGuo
 *  date : 2021-02-05 17:27:13
 *  description : { 请添加该类的描述 }
 */
class SplashFragment :SplashFMContract.IView, BaseDialogFragment<SplashFMContract.IView, SplashFMContract.IPresenter<SplashFMContract.IView>>() {

    //传递给Fragment的参数
    private lateinit var bundle:Bundle
    //P层对象，在createPresenter中初始化实例
    private lateinit var mPresenter: SplashFMContract.IPresenter<SplashFMContract.IView>

    /**
     * 创建Presenter实例
     */
    override fun createPresenter(): SplashFMContract.IPresenter<SplashFMContract.IView> {
        mPresenter = SplashFMPresenter()
        return mPresenter
    }

    /**
     * 返回当前实例
     */
    override fun createView(): SplashFMContract.IView {
        return this
    }

    /**
     * 获取Fragment对象
     */
    fun getInstance(): BaseDialogFragment<SplashFMContract.IView, SplashFMContract.IPresenter<SplashFMContract.IView>> {
        synchronized(SplashFragment::class){
            return SplashFragment()
        }
    }

    /**
     * 获取Fragment所在的Activity，BaseView接口方法
     */
    override fun getBaseActivity(): Context? {
        return activity
    }

    /**
     * 传递给Fragment的参数
     */
    override fun setBundleExtra(bundle: Bundle?) {
        this.bundle = bundle!!
    }

    /**
     * 设置layoutId
     * 示例： return R.layout.xxx
     */
    override fun initLayoutId(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 通过layoutId构建的View实例，可在此实例中findViewById，kotlin暂时可不管此方法
     */
    override fun initViews(view: View?) {
        TODO("Not yet implemented")
    }

    /**
     * 可在此方法中初始化页面数据，此方法执行在onViewCreated中
     */
    override fun initData() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * 可在此方法中设置页面监听，此方法执行在onViewCreated中
     */
    override fun initListener() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setDialogWidth(): Int {
        return ViewGroup.LayoutParams.MATCH_PARENT
    }

    override fun setDialogHeight(): Int {
        return ViewGroup.LayoutParams.MATCH_PARENT
    }

    override fun setOutSideAlpha(): Float? {
        return 1f
    }

    override fun setGravity(): Int {
        return Gravity.CENTER
    }

    override fun fragmentIOAnimation(): Int {
        return R.style.RightAnimation
    }
}