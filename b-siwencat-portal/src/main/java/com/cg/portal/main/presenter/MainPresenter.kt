package com.cg.portal.main.presenter

import com.cg.portal.main.contract.MainContract
import com.cg.portal.main.model.MainModel

/**
 *  author : ChenGuo
 *  date : 2021-02-06 10:20:42
 *  description : { 请添加该类的描述 }
 */
class MainPresenter : MainContract.IPresenter<MainContract.IView>() {

    //MVP中的model层实例
    private var mModel:MainModel? = null

    init {
        mModel = MainModel()
    }
}