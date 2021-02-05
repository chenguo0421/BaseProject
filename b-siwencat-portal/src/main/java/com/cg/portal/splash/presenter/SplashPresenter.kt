package com.cg.portal.splash.presenter

import com.cg.portal.splash.contract.SplashContract
import com.cg.portal.splash.model.SplashModel

/**
 *  author : ChenGuo
 *  date : 2021-02-05 17:53:58
 *  description : { 请添加该类的描述 }
 */
class SplashPresenter : SplashContract.IPresenter<SplashContract.IView>() {

    //MVP中的model层实例
    private var mModel:SplashModel? = null

    init {
        mModel = SplashModel()
    }
}