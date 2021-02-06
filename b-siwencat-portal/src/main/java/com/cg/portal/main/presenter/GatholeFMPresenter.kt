package com.cg.portal.main.presenter

import com.cg.portal.main.contract.GatholeFMContract
import com.cg.portal.main.model.GatholeFMModel

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:05:04
 *  description : { 请添加该类的描述 }
 */
class GatholeFMPresenter : GatholeFMContract.IPresenter<GatholeFMContract.IView>() {

    //MVP中的model层实例
    private var mModel:GatholeFMModel? = null

    init {
        mModel = GatholeFMModel()
    }
}