package com.cg.base.mvp.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.cg.base.mvp.base.intf.BaseView
import com.trello.rxlifecycle4.components.support.RxAppCompatActivity

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/8/27 20:22
 */
abstract class BaseActivity<V : BaseView, P : BasePresenter<V>> : RxAppCompatActivity() {
    private var mView: V? = null
    var mPresenter: P? = null
    protected val TAG = this.javaClass.name
    private val _bundleFragmentKey = "android:support:fragments"


    @SuppressLint("PrivateApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        savedInstanceState?.remove(_bundleFragmentKey)
        try {
            val decorViewClazz = Class.forName("com.android.internal.policy.DecorView")
            val field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor")
            field.isAccessible = true
            field.setInt(window.decorView, Color.TRANSPARENT) //改为透明
        } catch (e: Exception) {
        }
        super.onCreate(savedInstanceState)
        val view = LayoutInflater.from(this).inflate(initLayoutId(), null)
        setContentView(view)
        if (mPresenter == null) {
            mPresenter = createPresenter()
        }
        if (mView == null) {
            mView = createView()
        }
        mPresenter!!.attachView(mView!!)

        initView(view)

        initData()
        initListener()
    }


    protected abstract fun initView(view: View?)
    protected abstract fun initData()
    protected abstract fun initListener()
    protected abstract fun initLayoutId(): Int
    protected abstract fun createPresenter(): P
    protected abstract fun createView(): V


    fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int){
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
        supportFragmentManager.inTransaction{replace(frameId, fragment)}
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) = beginTransaction().func().commit()

    public override fun onDestroy() {
        mPresenter?.detachView()
        super.onDestroy()
    }

}