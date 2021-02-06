package com.cg.portal.main.view.fragment

import android.os.Bundle
import android.content.Context
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cg.base.mvp.base.BaseFragment
import com.cg.base.utils.ToastUtils
import com.cg.portal.R
import com.cg.portal.main.adapter.MyFunAdapter
import com.cg.portal.main.adapter.MyFunDetailAdapter
import com.cg.portal.main.adapter.MyToolsAdapter
import com.cg.portal.main.bean.MyMenus
import com.cg.portal.main.contract.MyFMContract
import com.cg.portal.main.presenter.MyFMPresenter
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import kotlinx.android.synthetic.main.portal_fragment_my.*

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:05:09
 *  description : { 请添加该类的描述 }
 */
class MyFragment :MyFMContract.IView, BaseFragment<MyFMContract.IView, MyFMContract.IPresenter<MyFMContract.IView>>() ,
    MyToolsAdapter.OnToolsItemClickListener, MyFunDetailAdapter.OnFunDetailItemClickListener{
    private var funAdapter: MyFunAdapter? = null
    private var toolAdapter: MyToolsAdapter? = null
    //传递给Fragment的参数
    private lateinit var bundle:Bundle

    companion object {
        //通过@JvmStatic注解，使得在Java中调用instance直接是像调用静态函数一样，
        //类似KLazilyDCLSingleton.getInstance(),如果不加注解，在Java中必须这样调用: KLazilyDCLSingleton.Companion.getInstance().
        @JvmStatic
        //获取Fragment单例对象，使用lazy属性代理，并指定LazyThreadSafetyMode为SYNCHRONIZED模式保证线程安全
        val instance: BaseFragment<MyFMContract.IView, MyFMContract.IPresenter<MyFMContract.IView>> by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { MyFragment() }
    }

    /**
     * 创建Presenter实例
     */
    override fun createPresenter(): MyFMContract.IPresenter<MyFMContract.IView> {
        return MyFMPresenter()
    }

    /**
     * 返回当前实例
     */
    override fun createView(): MyFMContract.IView {
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
        return R.layout.portal_fragment_my
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
        initToolsRecyclerViews()
        initFunRecyclerViews()
        refreshLayout.setRefreshHeader(ClassicsHeader(getBaseActivity()))
        refreshLayout.setRefreshFooter(ClassicsFooter(getBaseActivity()))
        refreshLayout.setOnRefreshListener { refreshlayout ->
            refreshlayout.finishRefresh(2000 /*,false*/) //传入false表示刷新失败
        }
        refreshLayout.setOnLoadMoreListener { refreshlayout ->
            refreshlayout.finishLoadMore(2000 /*,false*/) //传入false表示加载失败
        }
    }

    /**
     * 可在此方法中设置页面监听，此方法执行在onViewCreated中
     */
    override fun initListener() {

    }



    private fun initFunRecyclerViews() {
        activity?.let { it->
            val manager =
                LinearLayoutManager(context)
            function_rv.layoutManager = manager
            funAdapter = MyFunAdapter(activity, MyMenus.getFunMenus(it),this)
            function_rv.adapter = funAdapter
        }
    }

    private fun initToolsRecyclerViews() {
        activity?.let { it->
            val manager =
                GridLayoutManager(it, 4, RecyclerView.VERTICAL, false)
            tools_menu_rv.layoutManager = manager
            toolAdapter = MyToolsAdapter(it, MyMenus.getMyToolsMenus(it),this)
            tools_menu_rv.adapter = toolAdapter
        }

    }

    override fun onToolItemClick(position: Int) {
        ToastUtils.show("点击了：" + toolAdapter?.getItemData(position)?.name)
    }

    override fun onFunDetailItemClick(parentPosition: Int, position: Int) {
        ToastUtils.show("点击了：" + (funAdapter?.getItemData(parentPosition)?.contentMenu?.get(position)?.name))
    }

}