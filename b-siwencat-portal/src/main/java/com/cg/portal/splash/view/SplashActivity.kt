package com.cg.portal.splash.view

import android.content.Context
import android.content.Intent
import android.view.View
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.cg.base.mvp.base.BaseActivity
import com.cg.base.utils.Constant
import com.cg.base.utils.SharepreferenceUtils
import com.cg.log.CLog
import com.cg.portal.R
import com.cg.portal.splash.contract.SplashContract
import com.cg.portal.splash.presenter.SplashPresenter
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 *  author : ChenGuo
 *  date : 2021-02-06 09:54:36
 *  description : { 请添加该类的描述 }
 */
@Route(path = Constant.PortalConstant.Path_Portal_SplashActivity)
class SplashActivity : SplashContract.IView, BaseActivity<SplashContract.IView, SplashContract.IPresenter<SplashContract.IView>>() {

    /**
     * 创建Presenter实例，该方法执行在onCreate中
     */
    override fun createPresenter(): SplashContract.IPresenter<SplashContract.IView> {
        return SplashPresenter()
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
        return R.layout.portal_activity_splash
    }

    /**
     * 通过layoutId构建的View实例，可在此实例中findViewById，kotlin暂时可不管此方法
     */
    override fun initView(view: View?) {
    }

     /**
     * 可在此方法中初始化页面数据，此方法执行在onViewCreated中
     */
    override fun initData() {
         SharepreferenceUtils.put(this, Constant.PortalConstant.IS_LOGIN, false)
         gotoPortalActivity()
    }

    /**
     * 可在此方法中设置页面监听，此方法执行在onViewCreated中
     */
    override fun initListener() {
    }

    /**
     * 跳转到登录页
     */
    private fun gotoPortalActivity(){
        doAsync {
            uiThread {
                ARouter.getInstance().build(Constant.PortalConstant.Path_Portal_MainActivity)
                        .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .withTransition(0,0)
                        .navigation(this@SplashActivity, object :
                            com.alibaba.android.arouter.facade.callback.NavigationCallback {
                            override fun onArrival(postcard: Postcard?) {
                                CLog.i("")
                            }

                            override fun onFound(postcard: Postcard?) {
                                CLog.i("")
                            }

                            override fun onInterrupt(postcard: Postcard?) {
                                CLog.i("")
                            }

                            override fun onLost(postcard: Postcard?) {
                                CLog.i("")
                            }
                        })
//                finish()
            }
        }
    }

}