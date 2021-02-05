package com.cg.portal.splash.presenter

import com.cg.portal.splash.contract.SplashFMContract
import com.cg.portal.splash.model.SplashFMModel

/**
 *  author : ChenGuo
 *  date : 2021-02-05 17:27:13
 *  description : { 请添加该类的描述 }
 */
class SplashFMPresenter : SplashFMContract.IPresenter<SplashFMContract.IView>() {

    //MVP中的model层实例
    private var mModel:SplashFMModel? = null

    init {
        mModel = SplashFMModel()
    }
}