package com.cg.net.retrofit.utils

import com.cg.net.retrofit.base.BaseApi
import com.cg.net.retrofit.interceptor.CommonInterceptor
import com.cg.net.retrofit.interceptor.CookieInterceptor
import com.cg.net.retrofit.interceptor.LogInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*
import javax.security.cert.CertificateException

class RetrofitHelper {
    companion object{
        private fun <T> getService(baseUrl: String, service: Class<T>): T {

            val client = OkHttpClient.Builder()
                //自定义拦截器用于日志输出
                .addInterceptor(LogInterceptor())
                .addInterceptor(CommonInterceptor())
                .addInterceptor(CookieInterceptor())
                .connectTimeout(60,TimeUnit.SECONDS)
                .readTimeout(60,TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                //格式转换
                .addConverterFactory(GsonConverterFactory.create())
                //正常的retrofit返回的是call，此方法用于将call转化成Rxjava的Observable或其他类型
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
            return retrofit.create(service)
        }

        //okHttp3添加信任所有证书
        private fun <T> getSafeService(baseUrl: String, service: Class<T>): T {

            val client = getSafeOkHttpClient()

            val retrofit = Retrofit.Builder().baseUrl(baseUrl)
                //格式转换
                .addConverterFactory(GsonConverterFactory.create())
                //正常的retrofit返回的是call，此方法用于将call转化成Rxjava的Observable或其他类型
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
            return retrofit.create(service)
        }


        private fun getSafeOkHttpClient():OkHttpClient{
            try {
                val trustAllCerts:Array<X509TrustManager> = arrayOf(object : X509TrustManager{
                    @Throws(CertificateException::class)
                    override fun checkClientTrusted(
                        chain: Array<X509Certificate>, authType: String) {
                    }

                    @Throws(CertificateException::class)
                    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                    }

                    override fun getAcceptedIssuers(): Array<X509Certificate?> {
                        return arrayOfNulls(0)
                    }
                })

                val sc = SSLContext.getInstance("SSL")
                sc!!.init(null, trustAllCerts, SecureRandom())

                // Create all-trusting host name verifier
                val allHostsValid = HostnameVerifier { _, _ -> true }

                var sFactory = sc.socketFactory

                var builder = OkHttpClient.Builder()
                    .sslSocketFactory(sFactory, trustAllCerts[0])
                    .hostnameVerifier(allHostsValid)
                    .connectTimeout(60,TimeUnit.SECONDS)
                    .readTimeout(60,TimeUnit.SECONDS)
                    .writeTimeout(60,TimeUnit.SECONDS)
                    .addInterceptor(CommonInterceptor())
                    .addInterceptor(CookieInterceptor())
                return builder.build()

            }catch (e:Exception){
                e.printStackTrace()
            }
            return OkHttpClient()
        }

        //可用于多种不同种类的请求
        fun <T> apiService(cls:Class<T>): T {
            return getService(BaseApi.baseUrl, cls)
        }

        //可用于多种不同种类的请求
        fun <T> apiSafeService(cls:Class<T>): T {
            return getSafeService(BaseApi.baseUrl, cls)
        }
    }
}