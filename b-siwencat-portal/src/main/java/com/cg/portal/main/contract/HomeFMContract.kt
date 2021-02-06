package com.cg.portal.main.contract

import com.cg.base.mvp.base.BaseModel
import com.cg.base.mvp.base.BasePresenter
import com.cg.base.mvp.base.intf.BaseView
import com.cg.portal.main.bean.HomeBean

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:04:36
 *  description : { 请添加该类的描述 }
 */
class HomeFMContract {

    public interface IView:BaseView{

        fun onQueryHomeDataSuccess(bean: HomeBean)
    }


    public abstract class IPresenter<T> : BasePresenter<T>() {
        abstract fun queryHomeData()
    }


    public abstract class IModel:BaseModel(){

    }

}