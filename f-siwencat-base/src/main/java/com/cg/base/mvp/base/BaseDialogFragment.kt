package com.cg.base.mvp.base

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.cg.base.R
import com.cg.base.mvp.base.intf.BaseView
import com.trello.rxlifecycle4.components.support.RxDialogFragment


/**
 *  author : chenguo
 *  date : 2019/11/30 18:58
 *  description : { 请添加该类的描述 }
 */
abstract class BaseDialogFragment<V : BaseView, P : BasePresenter<V>> : RxDialogFragment() {
    private var fragmentTag = ""
    private var mView: V? = null
    var mPresenter: P? = null
    private var orientation = R.style.RightAnimation
    private val handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (mPresenter == null) {
            mPresenter = createPresenter()
        }

        if (mView == null) {
            mView = createView()
        }

        mPresenter!!.attachView(mView!!)

        orientation = fragmentIOAnimation()

        setStyle(STYLE_NO_TITLE, R.style.DialogCommonTitleBGStatue)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setWindowAnimations(orientation)
        }
        val v = inflater.inflate(initLayoutId(), container, false)
        initViews(v)
        return v
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initListener()
        setAttribute()
    }

    private fun setAttribute() {
        if (dialog != null && dialog!!.window != null) {
            dialog!!.setCanceledOnTouchOutside(false)
            dialog!!.setCancelable(false)
            dialog!!.window!!
                .setBackgroundDrawable(
                    ColorDrawable(
                        resources.getColor(
                            R.color.common_white,
                            resources.newTheme()
                        )
                    )
                )
            setDialogAttribute()
        }
    }

    private fun setDialogAttribute() {
        val attribute = dialog!!.window!!.attributes
        attribute.dimAmount = setOutSideAlpha()!!
        attribute.width = setDialogWidth()
        attribute.height = setDialogHeight()
        attribute.gravity = setGravity()
        dialog!!.window!!.attributes = attribute
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed({
            if (dialog != null && dialog!!.window != null) {
                dialog!!.window!!.setWindowAnimations(orientation)
            }
        }, 500)
    }


    override fun onStop() {
        super.onStop()
        if (dialog != null && dialog!!.window != null) {
            dialog!!.window!!.setWindowAnimations(0)
        }
    }

    /**
     * 解决 commit时出现的 IllegalStateException: Can not perform this action after onSaveInstanceState
     * @param manager
     * @param tag
     */
    override fun show(manager: FragmentManager, tag: String?) {
        if (isAdded) {
            val fragment = manager.findFragmentByTag(tag) as DialogFragment?
            if (fragment != null && fragment.dialog != null && !fragment.dialog!!.isShowing) {
                fragment.dialog!!.show()
            }
            return
        }
        try {
            val c = Class.forName("androidx.fragment.app.DialogFragment")
            val con = c.getConstructor()
            val obj = con.newInstance()
            val dismissed = c.getDeclaredField("mDismissed")
            dismissed.isAccessible = true
            dismissed[obj] = false
            val shownByMe = c.getDeclaredField("mShownByMe")
            shownByMe.isAccessible = true
            shownByMe[obj] = false
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val ft = manager.beginTransaction()
        ft.add(this, tag)
        ft.commitAllowingStateLoss()
    }


    override fun dismiss() {
        dismissAllowingStateLoss()
    }

    protected abstract fun createPresenter(): P
    protected abstract fun createView(): V
    protected abstract fun initViews(view: View?)
    protected abstract fun initLayoutId(): Int
    protected abstract fun initData()
    protected abstract fun initListener()
    protected abstract fun fragmentIOAnimation(): Int
    protected abstract fun setDialogWidth(): Int
    protected abstract fun setDialogHeight(): Int
    protected abstract fun setOutSideAlpha(): Float?
    protected abstract fun setGravity(): Int
    abstract fun setBundleExtra(bundle: Bundle?)


    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        mPresenter?.detachView()
        super.onDestroy()
    }
}