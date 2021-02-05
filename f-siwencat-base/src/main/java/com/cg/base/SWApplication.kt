package com.cg.base

import android.app.Application
import com.cg.log.CLog

/**
 * @ProjectName:    siwencat
 * @CreateDate:     2021/2/5 11:05
 * @Author:         ChenGuo
 * @Description:     java类作用描述
 * @Version:        1.0
 */
class SWApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init(){
        CLog.init(true)

    }
}