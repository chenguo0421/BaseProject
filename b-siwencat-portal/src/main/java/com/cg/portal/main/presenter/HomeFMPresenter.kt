package com.cg.portal.main.presenter

import com.cg.base.utils.GlobalParams
import com.cg.log.CLog
import com.cg.portal.main.bean.HomeBean
import com.cg.portal.main.contract.HomeFMContract
import com.cg.portal.main.model.HomeFMModel
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:04:36
 *  description : { 请添加该类的描述 }
 */
class HomeFMPresenter : HomeFMContract.IPresenter<HomeFMContract.IView>() {

    //MVP中的model层实例
    private var mModel:HomeFMModel? = null

    init {
        mModel = HomeFMModel()
    }

    override fun queryHomeData() {
        doAsync {
            val response = GlobalParams.getHomeDataResponse(getView()?.getBaseActivity()!!,"/json/assets_home_list.json")
            response?.let { CLog.d(it) }
            val list = Gson().fromJson(response, HomeBean::class.java)
            uiThread {
                getView()?.onQueryHomeDataSuccess(list)
            }
        }
    }
}