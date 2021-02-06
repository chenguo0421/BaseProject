package com.cg.portal.login.contract

import com.cg.base.mvp.base.BaseModel
import com.cg.base.mvp.base.BasePresenter
import com.cg.base.mvp.base.intf.BaseView

/**
 *  author : ChenGuo
 *  date : 2021-02-06 11:07:02
 *  description : { 请添加该类的描述 }
 */
class LoginFMContract {

    public interface IView:BaseView{

    }


    public abstract class IPresenter<T> : BasePresenter<T>() {

    }


    public abstract class IModel:BaseModel(){

    }

}