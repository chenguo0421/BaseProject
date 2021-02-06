package com.cg.portal.main.view

import android.content.Context
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.cg.base.mvp.base.BaseActivity
import com.cg.base.mvp.base.BaseDialogFragment
import com.cg.base.mvp.base.BaseFragment
import com.cg.base.utils.Constant
import com.cg.base.utils.SharepreferenceUtils
import com.cg.portal.R
import com.cg.portal.login.view.fragment.LoginFragment
import com.cg.portal.main.contract.MainContract
import com.cg.portal.main.presenter.MainPresenter
import com.cg.portal.main.view.fragment.*
import kotlinx.android.synthetic.main.portal_main_activity.*

/**
 *  author : ChenGuo
 *  date : 2021-02-06 10:20:42
 *  description : { 请添加该类的描述 }
 */
@Route(path = Constant.PortalConstant.Path_Portal_MainActivity)
class MainActivity : MainContract.IView, BaseActivity<MainContract.IView, MainContract.IPresenter<MainContract.IView>>() {

    private var storeFragment: BaseFragment<*, *>? = null
    private var catholeFragment: BaseFragment<*, *>? = null
    private var homeFragment: BaseFragment<*, *>? = null
    private var giftFragment: BaseFragment<*, *>? = null
    private var myFragment: BaseFragment<*, *>? = null
    private var currentIndex:Int = 0

    private var loginFragment: BaseDialogFragment<*, *>? = null

    /**
     * 创建Presenter实例，该方法执行在onCreate中
     */
    override fun createPresenter(): MainContract.IPresenter<MainContract.IView> {
        return MainPresenter()
    }

    /**
     * 返回当前实例，该方法执行在onCreate中，initLayoutId之后
     */
    override fun createView(): MainContract.IView {
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
        return R.layout.portal_main_activity
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
         changeTabByIndex(2)
    }

    /**
     * 可在此方法中设置页面监听，此方法执行在onViewCreated中
     */
    override fun initListener() {
        index_bottom_bar_store.setOnClickListener(TabOnClickListener(0))
        index_bottom_bar_cathole.setOnClickListener(TabOnClickListener(1))
        index_bottom_bar_home.setOnClickListener(TabOnClickListener(2))
        index_bottom_bar_gift.setOnClickListener(TabOnClickListener(3))
        index_bottom_bar_my.setOnClickListener(TabOnClickListener(4))
    }


    private fun changeTabByIndex(index:Int){
        when(index){
            0 -> {
                resetView()
                store_tv.setTextColor(resources.getColor(R.color.common_text_color_red,resources.newTheme()))
                index_bottom_bar_store_image.setImageResource(R.mipmap.icon_store_red)
                if (storeFragment == null) {
                    storeFragment = StoreFragment.instance
                }
                replaceFragment(storeFragment!!,R.id.portal_content)
            }
            1 -> {
                resetView()
                cathole_tv.setTextColor(resources.getColor(R.color.common_text_color_red,resources.newTheme()))
                index_bottom_bar_cathole_image.setImageResource(R.mipmap.icon_gathole_red)
                if (catholeFragment == null) {
                    catholeFragment = GatholeFragment.instance
                }
                replaceFragment(catholeFragment!!,R.id.portal_content)
            }
            2 -> {
                resetView()
                home_tv.setTextColor(resources.getColor(R.color.common_text_color_red,resources.newTheme()))
                index_bottom_bar_home.setImageResource(R.mipmap.icon_siwencat_red)
                if (homeFragment == null) {
                    homeFragment = HomeFragment.instance
                }
                replaceFragment(homeFragment!!,R.id.portal_content)
            }
            3 -> {
                resetView()
                gift_tv.setTextColor(resources.getColor(R.color.common_text_color_red,resources.newTheme()))
                index_bottom_bar_gift_image.setImageResource(R.mipmap.icon_gift_red)
                if (giftFragment == null) {
                    giftFragment = GiftFragment.instance
                }
                replaceFragment(giftFragment!!,R.id.portal_content)
            }
            4 -> {
//                if (!SharepreferenceUtils.getBoolean(this,Constant.PortalConstant.IS_LOGIN,false)){
//                    if (loginFragment == null) {
//                        loginFragment = LoginFragment.instance
//                    }
//                    loginFragment?.show(supportFragmentManager,"loginFragment1")
//                    return
//                }
                resetView()
                my_tv.setTextColor(resources.getColor(R.color.common_text_color_red,resources.newTheme()))
                index_bottom_bar_my_image.setImageResource(R.mipmap.icon_my_red)
                if (myFragment == null) {
                    myFragment = MyFragment.instance
                }
                replaceFragment(myFragment!!,R.id.portal_content)
            }
        }

        currentIndex = index
    }

    private fun resetView(){
        setAllDrawable2Gray()
        setAllTextColorGrat()
    }

    private fun setAllDrawable2Gray(){
        index_bottom_bar_store_image.setImageResource(R.mipmap.icon_store_gray)
        index_bottom_bar_cathole_image.setImageResource(R.mipmap.icon_gathole_gray)
        index_bottom_bar_home.setImageResource(R.mipmap.icon_siwencat_gray)
        index_bottom_bar_gift_image.setImageResource(R.mipmap.icon_gift_gray)
        index_bottom_bar_my_image.setImageResource(R.mipmap.icon_my_gray)
    }

    private fun  setAllTextColorGrat(){
        store_tv.setTextColor(resources.getColor(R.color.common_text_color_cdcdcd,resources.newTheme()))
        cathole_tv.setTextColor(resources.getColor(R.color.common_text_color_cdcdcd,resources.newTheme()))
        home_tv.setTextColor(resources.getColor(R.color.common_text_color_cdcdcd,resources.newTheme()))
        gift_tv.setTextColor(resources.getColor(R.color.common_text_color_cdcdcd,resources.newTheme()))
        my_tv.setTextColor(resources.getColor(R.color.common_text_color_cdcdcd,resources.newTheme()))
    }

    inner class TabOnClickListener(private val index: Int) : View.OnClickListener{

        override fun onClick(v: View?) {
            if (index == currentIndex){
                return
            }
            changeTabByIndex(index)
        }

    }
}