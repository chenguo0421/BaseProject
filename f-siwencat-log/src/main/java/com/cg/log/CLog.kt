package com.cg.log

import com.orhanobut.logger.Logger

/**
 * @ProjectName:    siwencat
 * @CreateDate:     2021/2/5 10:56
 * @Author:         ChenGuo
 * @Description:     java类作用描述
 * @Version:        1.0
 */
object CLog {

    private var isDebug: Boolean = false

    fun init(isDebug:Boolean){
        CLog.isDebug = isDebug
    }

    fun i(message: String) {
        if (isDebug){
            Logger.i(message)
        }
    }

    fun d(message: String) {
        if (isDebug){
            Logger.d(message)
        }
    }

    fun e(message: String) {
        if (isDebug){
            Logger.e(message)
        }
    }

    fun w(message: String) {
        if (isDebug){
            Logger.w(message)
        }
    }
}