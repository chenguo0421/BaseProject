package com.cg.portal.main.contract

import com.cg.base.mvp.base.BaseModel
import com.cg.base.mvp.base.BasePresenter
import com.cg.base.mvp.base.intf.BaseView
import com.cg.portal.main.bean.StoreProductsBean

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:05:18
 *  description : { 请添加该类的描述 }
 */
class StoreFMContract {

    public interface IView:BaseView{
        fun onLoadProductListSuccess(list: ArrayList<StoreProductsBean>)
    }


    public abstract class IPresenter<T> : BasePresenter<T>() {
        abstract fun getStoreProductsResponse()
    }


    public abstract class IModel:BaseModel(){

    }

}