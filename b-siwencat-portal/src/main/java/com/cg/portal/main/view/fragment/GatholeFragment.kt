package com.cg.portal.main.view.fragment

import android.os.Bundle
import android.content.Context
import android.view.View
import com.cg.base.mvp.base.BaseFragment
import com.cg.portal.R
import com.cg.portal.main.contract.GatholeFMContract
import com.cg.portal.main.presenter.GatholeFMPresenter
import kotlinx.android.synthetic.main.portal_fragment_gathole.*

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:05:04
 *  description : { 请添加该类的描述 }
 */
class GatholeFragment :GatholeFMContract.IView, BaseFragment<GatholeFMContract.IView, GatholeFMContract.IPresenter<GatholeFMContract.IView>>() ,
    View.OnClickListener {

    //传递给Fragment的参数
    private lateinit var bundle:Bundle

    companion object {
        //通过@JvmStatic注解，使得在Java中调用instance直接是像调用静态函数一样，
        //类似KLazilyDCLSingleton.getInstance(),如果不加注解，在Java中必须这样调用: KLazilyDCLSingleton.Companion.getInstance().
        @JvmStatic
        //获取Fragment单例对象，使用lazy属性代理，并指定LazyThreadSafetyMode为SYNCHRONIZED模式保证线程安全
        val instance: BaseFragment<GatholeFMContract.IView, GatholeFMContract.IPresenter<GatholeFMContract.IView>> by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { GatholeFragment() }
    }

    /**
     * 创建Presenter实例
     */
    override fun createPresenter(): GatholeFMContract.IPresenter<GatholeFMContract.IView> {
        return GatholeFMPresenter()
    }

    /**
     * 返回当前实例
     */
    override fun createView(): GatholeFMContract.IView {
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
        return R.layout.portal_fragment_gathole
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
        iv_collect.setOnClickListener(this)
        iv_original.setOnClickListener(this)
    }


    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_collect -> {
                activity?.let { act ->
                    iv_collect.setBackgroundResource(R.drawable.common_circle_half_left_red)
                    iv_collect.setTextColor(act.getColor(R.color.common_white))
                    iv_original.setBackgroundResource(R.drawable.common_circle_half_border_right_red)
                    iv_original.setTextColor(act.getColor(R.color.common_red))
                }
            }
            R.id.iv_original -> {
                activity?.let { act ->
                    iv_collect.setBackgroundResource(R.drawable.common_circle_half_border_left_red)
                    iv_collect.setTextColor(act.getColor(R.color.common_red))
                    iv_original.setBackgroundResource(R.drawable.common_circle_half_right_red)
                    iv_original.setTextColor(act.getColor(R.color.common_white))
                }
            }
        }
    }



}