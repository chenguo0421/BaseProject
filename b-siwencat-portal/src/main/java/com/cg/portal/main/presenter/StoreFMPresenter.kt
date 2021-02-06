package com.cg.portal.main.presenter

import com.cg.base.utils.GlobalParams
import com.cg.log.CLog
import com.cg.portal.main.bean.StoreProductsBean
import com.cg.portal.main.contract.StoreFMContract
import com.cg.portal.main.model.StoreFMModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:05:18
 *  description : { 请添加该类的描述 }
 */
class StoreFMPresenter : StoreFMContract.IPresenter<StoreFMContract.IView>() {

    //MVP中的model层实例
    private var mModel:StoreFMModel? = null

    init {
        mModel = StoreFMModel()
    }

    override fun getStoreProductsResponse() {
        doAsync {
            val response = GlobalParams.getStoreProductsResponse(getView()?.getBaseActivity()!!,"/json/assets_store_productlist.json")
            response?.let { CLog.d(it) }
            val type = object : TypeToken<ArrayList<StoreProductsBean>>(){}.type
            val list = Gson().fromJson<ArrayList<StoreProductsBean>>(response,type)
            uiThread {
                getView()?.onLoadProductListSuccess(list)
            }
        }
    }
}