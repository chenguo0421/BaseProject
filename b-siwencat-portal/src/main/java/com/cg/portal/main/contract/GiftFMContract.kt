package com.cg.portal.main.contract

import com.cg.base.mvp.base.BaseModel
import com.cg.base.mvp.base.BasePresenter
import com.cg.base.mvp.base.intf.BaseView
import com.cg.portal.main.bean.GiftDJYPBean

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:04:58
 *  description : { 请添加该类的描述 }
 */
class GiftFMContract {

    public interface IView:BaseView{

        fun onLoadGiftDJYPDataSuccess(bean: GiftDJYPBean?)
    }


    public abstract class IPresenter<T> : BasePresenter<T>() {
        abstract fun getGiftDJYPData()

    }


    public abstract class IModel:BaseModel(){

    }

}