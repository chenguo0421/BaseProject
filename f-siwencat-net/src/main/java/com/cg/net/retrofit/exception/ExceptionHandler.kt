package com.cg.net.retrofit.exception

import android.net.ParseException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import javax.net.ssl.SSLHandshakeException

/**
 * Discription  {}
 * author  chenguo7
 * Date  2019/11/25 15:11
 */
class ExceptionHandler {

    private val _unAuth = 401
    private val _forbidden = 403
    private val _notFound = 404
    private val _requestTimeout = 408
    private val _internalServerError = 500
    private val _badGateWay = 502
    private val _serviceUnAvailable = 503
    private val _gateWayTimeout = 504


    fun handleException(e: Throwable): ResponseThrowable {
        val throwable: ResponseThrowable
        if (e is HttpException) {
            throwable = ResponseThrowable(Error.HTTP_ERROR.type)
            when (e.code()) {
                _unAuth, _forbidden, _notFound, _requestTimeout, _internalServerError, _badGateWay, _serviceUnAvailable, _gateWayTimeout -> throwable.message =
                    "网络错误"//这里其实可以做详细的区分
                else -> throwable.message = "网络错误"
            }
            return throwable
        } else if (e is ServerException) {
            throwable = ResponseThrowable(e.code)
            throwable.message = e.message
            return throwable
        } else if (e is JsonParseException || e is JSONException || e is ParseException) {
            throwable = ResponseThrowable(Error.PARSE_ERROR.type)
            throwable.message = Error.PARSE_ERROR.description
            return throwable
        } else if (e is ConnectException) {
            throwable = ResponseThrowable(Error.NETWORK_ERROR.type)
            throwable.message = Error.NETWORK_ERROR.description
            return throwable
        } else if (e is SSLHandshakeException) {
            throwable = ResponseThrowable(Error.SSL_ERROR.type)
            throwable.message = Error.SSL_ERROR.description
            return throwable
        } else {
            throwable = ResponseThrowable(Error.UNKnow.type)
            throwable.message = Error.UNKnow.description
            return throwable
        }
    }


    class ResponseThrowable(code: Int) : Exception() {
        private var code = 0
        override var message: String? = null

        init {
            this.code = code
        }

    }

    class ServerException(code:Int,msg:String?) : RuntimeException() {
        var code = 0
        override var message: String? = null
        init {
            this.code = code
            this.message = msg
        }
    }

    enum class Error(val description: String, val type: Int) {

        UNKnow("UNKnow", 1000),
        PARSE_ERROR("PARSE_ERROR", 1001),
        NETWORK_ERROR("NETWORK_ERROR", 1002),
        HTTP_ERROR("HTTP_ERROR", 1003),
        SSL_ERROR("SSL_ERROR", 1004)


    }
}