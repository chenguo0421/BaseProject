package com.cg.portal.main.view.fragment

import android.os.Bundle
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.cg.base.mvp.base.BaseFragment
import com.cg.base.utils.GlobalParams
import com.cg.base.utils.ToastUtils
import com.cg.base.widget.itemdecoration.RecycleGridDivider
import com.cg.base.widget.recyclerview.NoScrollGridLayoutManager
import com.cg.portal.R
import com.cg.portal.intf.OnItemClickListener
import com.cg.portal.main.adapter.GiftDJYPAdapter
import com.cg.portal.main.adapter.GiftItemAdapter
import com.cg.portal.main.adapter.GiftTypeAdapter
import com.cg.portal.main.bean.GiftDJYPBean
import com.cg.portal.main.contract.GiftFMContract
import com.cg.portal.main.presenter.GiftFMPresenter
import com.pdog.dimension.dp
import kotlinx.android.synthetic.main.portal_fragment_gift.*

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:04:58
 *  description : { 请添加该类的描述 }
 */
class GiftFragment :GiftFMContract.IView, BaseFragment<GiftFMContract.IView, GiftFMContract.IPresenter<GiftFMContract.IView>>() ,
    OnItemClickListener<GiftDJYPBean.GiftTypeItem> {
    private lateinit var giftItemAdapter: GiftItemAdapter
    private lateinit var giftTypeAdapter: GiftTypeAdapter
    private lateinit var giftMenuAdapter: GiftDJYPAdapter
    //传递给Fragment的参数
    private lateinit var bundle:Bundle

    companion object {
        //通过@JvmStatic注解，使得在Java中调用instance直接是像调用静态函数一样，
        //类似KLazilyDCLSingleton.getInstance(),如果不加注解，在Java中必须这样调用: KLazilyDCLSingleton.Companion.getInstance().
        @JvmStatic
        //获取Fragment单例对象，使用lazy属性代理，并指定LazyThreadSafetyMode为SYNCHRONIZED模式保证线程安全
        val instance: BaseFragment<GiftFMContract.IView, GiftFMContract.IPresenter<GiftFMContract.IView>> by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { GiftFragment() }
    }

    /**
     * 创建Presenter实例
     */
    override fun createPresenter(): GiftFMContract.IPresenter<GiftFMContract.IView> {
        return GiftFMPresenter()
    }

    /**
     * 返回当前实例
     */
    override fun createView(): GiftFMContract.IView {
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
        return R.layout.portal_fragment_gift
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
        rv_giftItem.addItemDecoration(RecycleGridDivider(5.dp))
        initTopBanner()
        mPresenter?.getGiftDJYPData()
    }

    /**
     * 可在此方法中设置页面监听，此方法执行在onViewCreated中
     */
    override fun initListener() {
    }


    override fun onLoadGiftDJYPDataSuccess(bean: GiftDJYPBean?) {
        activity?.let { act ->
            bean?.menus?.let {
                giftMenuAdapter = GiftDJYPAdapter(act,bean.menus!!)
                val manager = GridLayoutManager(getBaseActivity(),2, GridLayoutManager.VERTICAL,false)
                rv_productType.layoutManager = manager
                rv_productType.adapter = giftMenuAdapter
            }

            bean?.centerBanner?.let {
                Glide.with(act).load(bean.centerBanner!!).into( iv_centerBanner)
            }

            bean?.giftList?.let {
                giftTypeAdapter = GiftTypeAdapter(act,bean.giftList!!,this)
                val manager = GridLayoutManager(getBaseActivity(),3, GridLayoutManager.VERTICAL,false)
                rv_giftType.layoutManager = manager
                rv_giftType.adapter = giftTypeAdapter

                bean.giftList!![0].let {
                    giftTypeAdapter.setCurrentSelect(0)
                    initGiftItemList(0)
                }
            }
        }

    }

    private fun initGiftItemList(i: Int) {
        activity?.let { act ->
            giftTypeAdapter.getChildList(i)?.let {
                val list:ArrayList<GiftDJYPBean.GiftTypeItem.GiftItem> = ArrayList( giftTypeAdapter.getChildList(i)!!)
                val gridLayoutManager = NoScrollGridLayoutManager(act,2)
                gridLayoutManager.setScrollEnabled(false)
                rv_giftItem.layoutManager = gridLayoutManager
                giftItemAdapter = GiftItemAdapter(act,list)
                rv_giftItem.adapter = giftItemAdapter
            }
        }
    }


    private fun initTopBanner() {
        activity?.let { act ->
            Glide.with(act).load("file:///android_asset/img/assets_gift_image1.png").into(iv_header_bg)
            banner.initBannerImageView(GlobalParams.getGiftBannerUrlList()) {
                ToastUtils.show("onclick : $it")
            }
        }

    }

    override fun onItemClick(item: GiftDJYPBean.GiftTypeItem, position: Int) {
        giftTypeAdapter.setCurrentSelect(position)
        initGiftItemList(position)
    }
}