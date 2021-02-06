package com.cg.portal.login.presenter

import com.cg.portal.login.contract.LoginFMContract
import com.cg.portal.login.model.LoginFMModel

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:07:02
 *  description : { 请添加该类的描述 }
 */
class LoginFMPresenter : LoginFMContract.IPresenter<LoginFMContract.IView>() {

    //MVP中的model层实例
    private var mModel:LoginFMModel? = null

    init {
        mModel = LoginFMModel()
    }
}