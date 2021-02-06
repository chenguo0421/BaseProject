package com.cg.portal.main.presenter

import com.cg.base.utils.GlobalParams
import com.cg.log.CLog
import com.cg.portal.main.bean.GiftDJYPBean
import com.cg.portal.main.contract.GiftFMContract
import com.cg.portal.main.model.GiftFMModel
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:04:58
 *  description : { 请添加该类的描述 }
 */
class GiftFMPresenter : GiftFMContract.IPresenter<GiftFMContract.IView>() {

    //MVP中的model层实例
    private var mModel:GiftFMModel? = null

    init {
        mModel = GiftFMModel()
    }

    override fun getGiftDJYPData() {
        doAsync {
            val response = GlobalParams.getGiftProductsResponse(getView()?.getBaseActivity()!!,"/json/assets_gift_djyp")
            response?.let { CLog.d(it) }
            val list = Gson().fromJson(response, GiftDJYPBean::class.java)
            uiThread {
                getView()?.onLoadGiftDJYPDataSuccess(list)
            }
        }
    }
}