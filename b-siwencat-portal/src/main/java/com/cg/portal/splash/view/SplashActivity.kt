package com.cg.portal.splash.view

import android.content.Context
import android.view.View
import com.cg.base.mvp.base.BaseActivity
import com.cg.portal.splash.contract.SplashContract
import com.cg.portal.splash.presenter.SplashPresenter

/**
 *  author : ChenGuo
 *  date : 2021-02-05 17:53:58
 *  description : { 请添加该类的描述 }
 */
class SplashActivity : SplashContract.IView, BaseActivity<SplashContract.IView, SplashContract.IPresenter<SplashContract.IView>>() {

    //P层对象，在createPresenter中初始化实例
    private lateinit var mPresenter: SplashContract.IPresenter<SplashContract.IView>

    /**
     * 创建Presenter实例，该方法执行在onCreate中
     */
    override fun createPresenter(): SplashContract.IPresenter<SplashContract.IView> {
        mPresenter = SplashPresenter()
        return mPresenter
    }

    /**
     * 返回当前实例，该方法执行在onCreate中，initLayoutId之后
     */
    override fun createView(): SplashContract.IView {
        return this
    }

    /**
     * 返回当前实例，BaseView接口方法
     */
    override fun getBaseActivity(): Context {
        return this
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
    override fun initView(view: View?) {
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

}