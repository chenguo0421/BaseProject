package com.cg.portal.main.view.fragment

import android.os.Bundle
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cg.base.mvp.base.BaseFragment
import com.cg.base.utils.GlobalParams
import com.cg.base.utils.ToastUtils
import com.cg.base.widget.banner.RecyclerViewBannerBase
import com.cg.base.widget.recyclerview.NoScrollLinearLayoutManager
import com.cg.portal.R
import com.cg.portal.main.adapter.StoreProductAdapter
import com.cg.portal.main.bean.StoreProductsBean
import com.cg.portal.main.contract.StoreFMContract
import com.cg.portal.main.presenter.StoreFMPresenter
import kotlinx.android.synthetic.main.portal_fragment_store.*

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:05:18
 *  description : { 请添加该类的描述 }
 */
class StoreFragment :StoreFMContract.IView, BaseFragment<StoreFMContract.IView, StoreFMContract.IPresenter<StoreFMContract.IView>>() {

    private lateinit var adapter: StoreProductAdapter

    //传递给Fragment的参数
    private lateinit var bundle:Bundle

    companion object {
        //通过@JvmStatic注解，使得在Java中调用instance直接是像调用静态函数一样，
        //类似KLazilyDCLSingleton.getInstance(),如果不加注解，在Java中必须这样调用: KLazilyDCLSingleton.Companion.getInstance().
        @JvmStatic
        //获取Fragment单例对象，使用lazy属性代理，并指定LazyThreadSafetyMode为SYNCHRONIZED模式保证线程安全
        val instance: BaseFragment<StoreFMContract.IView, StoreFMContract.IPresenter<StoreFMContract.IView>> by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { StoreFragment() }
    }

    /**
     * 创建Presenter实例
     */
    override fun createPresenter(): StoreFMContract.IPresenter<StoreFMContract.IView> {
        return StoreFMPresenter()
    }

    /**
     * 返回当前实例
     */
    override fun createView(): StoreFMContract.IView {
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
        return R.layout.portal_fragment_store
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
        activity?.let { it1 ->
            val divider = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
            divider.setDrawable(ContextCompat.getDrawable(it1, R.drawable.common_rv_divider_f2f2f2_ten)!!)
            product_rv.addItemDecoration(divider)
            initTopBanner()
            initCenterBanner()
            initProductList()
        }
    }

    /**
     * 可在此方法中设置页面监听，此方法执行在onViewCreated中
     */
    override fun initListener() {
        buy_iv.setOnClickListener{
            val height = product_rv.height
        }
        sale_iv.setOnClickListener{
        }
        shopcar_iv.setOnClickListener{
        }
    }


    override fun onLoadProductListSuccess(list: ArrayList<StoreProductsBean>) {
        activity?.let { it1 ->
            adapter = StoreProductAdapter(it1,list)
            val manager = NoScrollLinearLayoutManager(it1)
            manager.orientation = RecyclerView.VERTICAL
            manager.setScrollEnabled(false)
            product_rv.layoutManager = manager
            product_rv.adapter = adapter
        }

    }

    private fun initProductList() {
        mPresenter?.getStoreProductsResponse()
    }


    private fun initCenterBanner() {
        context?.let { Glide.with(it).load("file:///android_asset/img/assets_store_image27.png").into(center_banner_iv) }
    }

    private fun initTopBanner() {
        context?.let { Glide.with(it).load("file:///android_asset/img/assets_store_image12.png").into(header_bg_iv) }
        banner.initBannerImageView(GlobalParams.getStoreBannerUrlList(), RecyclerViewBannerBase.OnBannerItemClickListener {
            ToastUtils.show("onclick : $it")
        })
    }


}