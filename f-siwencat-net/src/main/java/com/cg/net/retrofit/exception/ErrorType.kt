package com.cg.net.retrofit.exception

import android.content.Context
import androidx.annotation.StringRes
import com.cg.net.R
import com.cg.net.retrofit.bean.ErrorBean

/**
 *  author : chenguo
 *  date : 2019/12/1 19:29
 *  description : { 请添加该类的描述 }
 */
enum class ErrorType(val code: Int, @param:StringRes private val messageId: Int) {
    //根据实际情况进行增删
    INTERNAL_SERVER_ERROR(500, R.string.service_error),
    BAD_GATEWAY(502, R.string.service_error),
    NOT_FOUND(404, R.string.not_found),
    CONNECTION_TIMEOUT(408, R.string.timeout),
    NETWORK_NOT_CONNECT(499, R.string.network_wrong),
    UNEXPECTED_ERROR(700, R.string.unexpected_error);

    private val _defaultCode = 1
    fun getApiErrorModel(context: Context): ErrorBean {
        return ErrorBean(_defaultCode, context.getString(messageId))
    }
}