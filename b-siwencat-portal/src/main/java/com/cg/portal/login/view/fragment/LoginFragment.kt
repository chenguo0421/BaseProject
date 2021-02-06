package com.cg.portal.login.view.fragment

import android.os.Bundle
import android.content.Context
import android.view.View
import android.view.Gravity
import android.view.ViewGroup
import com.cg.base.mvp.base.BaseDialogFragment
import com.cg.portal.R
import com.cg.portal.login.contract.LoginFMContract
import com.cg.portal.login.presenter.LoginFMPresenter

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:07:02
 *  description : { 请添加该类的描述 }
 */
class LoginFragment :LoginFMContract.IView, BaseDialogFragment<LoginFMContract.IView, LoginFMContract.IPresenter<LoginFMContract.IView>>() {

    //传递给Fragment的参数
    private lateinit var bundle:Bundle

    companion object {
        //通过@JvmStatic注解，使得在Java中调用instance直接是像调用静态函数一样，
        //类似KLazilyDCLSingleton.getInstance(),如果不加注解，在Java中必须这样调用: KLazilyDCLSingleton.Companion.getInstance().
        @JvmStatic
        //获取Fragment单例对象，使用lazy属性代理，并指定LazyThreadSafetyMode为SYNCHRONIZED模式保证线程安全
        val instance: BaseDialogFragment<LoginFMContract.IView, LoginFMContract.IPresenter<LoginFMContract.IView>> by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { LoginFragment() }
    }

    /**
     * 创建Presenter实例
     */
    override fun createPresenter(): LoginFMContract.IPresenter<LoginFMContract.IView> {
        return LoginFMPresenter()
    }

    /**
     * 返回当前实例
     */
    override fun createView(): LoginFMContract.IView {
        return this
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
    }

    /**
     * 可在此方法中初始化页面数据，此方法执行在onViewCreated中
     */
    override fun initData() {
    }

    /**
     * 可在此方法中设置页面监听，此方法执行在onViewCreated中
     */
    override fun initListener() {
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