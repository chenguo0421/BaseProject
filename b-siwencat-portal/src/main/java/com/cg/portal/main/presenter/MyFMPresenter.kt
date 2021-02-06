package com.cg.portal.main.presenter

import com.cg.portal.main.contract.MyFMContract
import com.cg.portal.main.model.MyFMModel

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:05:09
 *  description : { 请添加该类的描述 }
 */
class MyFMPresenter : MyFMContract.IPresenter<MyFMContract.IView>() {

    //MVP中的model层实例
    private var mModel:MyFMModel? = null

    init {
        mModel = MyFMModel()
    }
}