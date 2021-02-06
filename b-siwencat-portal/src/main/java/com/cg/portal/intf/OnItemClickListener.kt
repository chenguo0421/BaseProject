package com.cg.portal.intf

/**
 * @ProjectName:    siwencat
 * @CreateDate:     2021/2/6 11:48
 * @Author:         ChenGuo
 * @Description:     java类作用描述
 * @Version:        1.0
 */
interface OnItemClickListener<T> {
    fun onItemClick(item:T,position:Int)
}