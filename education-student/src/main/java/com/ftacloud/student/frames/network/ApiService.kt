package com.ftacloud.student.frames.network

import com.ftacloud.student.frames.network.request.FastLogin
import com.ftacloud.student.frames.network.request.GetVerifyCode
import com.ftacloud.student.storage.entity.User
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.sugar.library.frames.network.Response
import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * Created by Wangsw on 2020/5/22 0022 15:00.
 * </br>
 * 调用的api接口
 * http://127.0.0.1:52001/api/account/fastLogin
 */
interface ApiService {
    companion object {

        /**
         * 微信相关接口
         */
        const val API_WECHAT_OFFICIAL = "wechat"

        private const val USE_CACHED = "cache:60"


        private const val API_ACCOUNT_URI = "api/account"
        private const val API_SMS_URI = "api/sms"

        const val WECHAT_OFFICIAL_API = "https://api.weixin.qq.com"

    }

    /**
     * 获取验证码
     */
    @POST("$API_SMS_URI/sendvc")
    fun getVerifyCode(@Body json: GetVerifyCode?): Flowable<Response<JsonElement>>


    /**
     * 快速登陆
     */
    @POST("$API_ACCOUNT_URI/fastLogin")
    fun loginFast(@Body fastLogin: FastLogin?): Flowable<Response<JsonObject>>


}