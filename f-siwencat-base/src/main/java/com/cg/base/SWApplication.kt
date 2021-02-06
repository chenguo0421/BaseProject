package com.cg.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.cg.log.CLog
import com.hjq.toast.ToastUtils
import com.hjq.toast.style.ToastAliPayStyle

/**
 * @ProjectName:    siwencat
 * @CreateDate:     2021/2/5 11:05
 * @Author:         ChenGuo
 * @Description:     java类作用描述
 * @Version:        1.0
 */
class SWApplication : Application() {

    private val isDebug:Boolean = false

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init(){
        CLog.init(true)
        initToast()
        initARouter()
    }

    /**
     * 初始化全局Toast
     */
    private fun initToast() {
        ToastUtils.init(this)
        ToastUtils.initStyle(ToastAliPayStyle(this@SWApplication))
    }


    /**
     * 初始化路由
     */
    private fun initARouter() {
        //初始化ARouter
        if (isDebug) {
            //一定要在ARouter.init之前调用openDebug
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this@SWApplication)
    }


}