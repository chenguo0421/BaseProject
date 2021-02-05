package com.cg.base.utils

import android.view.Gravity
import com.hjq.toast.ToastUtils

/**
 * @ProjectName:    siwencat
 * @CreateDate:     2021/2/5 16:02
 * @Author:         ChenGuo
 * @Description:     java类作用描述
 * @Version:        1.0
 */
object ToastUtils {

    /**
     * Toast提示 居中
     */
    fun showToast(message:String){
        ToastUtils.setGravity(Gravity.CENTER,0,0)
        ToastUtils.show(message)
    }

    /**
     * Toast提示 偏下
     */
    fun show(message:String){
        ToastUtils.setGravity(Gravity.CENTER,0,200)
        ToastUtils.show(message)
    }
}