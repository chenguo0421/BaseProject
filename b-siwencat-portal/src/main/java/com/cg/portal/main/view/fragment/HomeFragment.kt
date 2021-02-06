package com.cg.portal.main.view.fragment

import android.os.Bundle
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cg.base.mvp.base.BaseFragment
import com.cg.base.utils.ToastUtils
import com.cg.base.widget.banner.RecyclerViewBannerBase
import com.cg.base.widget.recyclerview.NoScrollLinearLayoutManager
import com.cg.portal.R
import com.cg.portal.main.adapter.HomeListAdapter
import com.cg.portal.main.bean.HomeBean
import com.cg.portal.main.contract.HomeFMContract
import com.cg.portal.main.presenter.HomeFMPresenter
import kotlinx.android.synthetic.main.portal_fragment_home.*

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:04:36
 *  description : { 请添加该类的描述 }
 */
class HomeFragment :HomeFMContract.IView, BaseFragment<HomeFMContract.IView, HomeFMContract.IPresenter<HomeFMContract.IView>>() {
    private lateinit var adapter: HomeListAdapter
    //传递给Fragment的参数
    private lateinit var bundle:Bundle

    companion object {
        //通过@JvmStatic注解，使得在Java中调用instance直接是像调用静态函数一样，
        //类似KLazilyDCLSingleton.getInstance(),如果不加注解，在Java中必须这样调用: KLazilyDCLSingleton.Companion.getInstance().
        @JvmStatic
        //获取Fragment单例对象，使用lazy属性代理，并指定LazyThreadSafetyMode为SYNCHRONIZED模式保证线程安全
        val instance: BaseFragment<HomeFMContract.IView, HomeFMContract.IPresenter<HomeFMContract.IView>> by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { HomeFragment() }
    }

    /**
     * 创建Presenter实例
     */
    override fun createPresenter(): HomeFMContract.IPresenter<HomeFMContract.IView> {
        return HomeFMPresenter()
    }

    /**
     * 返回当前实例
     */
    override fun createView(): HomeFMContract.IView {
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
        return R.layout.portal_fragment_home
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
        mPresenter?.queryHomeData()
    }

    /**
     * 可在此方法中设置页面监听，此方法执行在onViewCreated中
     */
    override fun initListener() {
    }


    override fun onQueryHomeDataSuccess(bean: HomeBean) {
        setTopBanner(bean)
        setCenterBanner(bean)
        setDataList(bean)
    }

    private fun setDataList(bean: HomeBean) {
        activity?.let { act ->
            bean.list?.let {
                adapter = HomeListAdapter(act,bean.list!!)
                val manager = NoScrollLinearLayoutManager(act)
                manager.orientation = RecyclerView.VERTICAL
                manager.setScrollEnabled(false)
                val divider = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
                divider.setDrawable(ContextCompat.getDrawable(act, R.drawable.common_rv_divider_f2f2f2_ten)!!)
                rv_list.layoutManager = manager
                rv_list.adapter = adapter
                rv_list.addItemDecoration(divider)
            }
        }
    }

    private fun setCenterBanner(bean: HomeBean) {
        activity?.let { act ->
            bean.centerBanner?.let {
                Glide.with(act).load(bean.centerBanner).into(center_banner_iv)
            }
        }
    }

    private fun setTopBanner(bean: HomeBean) {
        bean.banners?.let {
            banner.initBannerImageView(it, RecyclerViewBannerBase.OnBannerItemClickListener {
                ToastUtils.show("onclick : $it")
            })
        }
    }


}