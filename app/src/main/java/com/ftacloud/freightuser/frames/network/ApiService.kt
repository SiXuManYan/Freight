package com.ftacloud.freightuser.frames.network

import android.os.Build
import com.ftacloud.freightuser.storage.entity.User
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.sugar.library.frames.network.Response
import com.sugar.library.util.Constants
import io.reactivex.Flowable
import retrofit2.http.*


/**
 * Created by Wangsw on 2020/5/22 0022 15:00.
 * </br>
 * 调用的api接口
 */
interface ApiService {
    companion object {

        /**
         * 微信相关接口
         */
        const val API_WECHAT_OFFICIAL = "wechat"

        private const val USE_CACHED = "cache:60"

        /**
         * 云账户API
         */
        private const val API_ACCOUNT_URI = "api/account"

         const val  WECHAT_OFFICIAL_API = "https://api.weixin.qq.com"

    }

    /**
     * 校验账户是否存在
     */
    @POST("$API_ACCOUNT_URI/existed")
    fun checkAccountIsExisted(@Query("username") username: String?): Flowable<Response<JsonObject>>


    /**
     * 注册
     * @Field
     */
    @POST("$API_ACCOUNT_URI/registry")
    fun register(
        @Query("username") username: String?,
        @Query("passwd") passwd: String?,
        @Query("pushDeviceId") pushDeviceId: String?,
        @Query("cityCode") cityCode: String? = "大连市",
        @Query("location") location: ArrayList<Long>? = ArrayList(),
        @Query("city") city: String?,
        @Query("uniqueCode") uniqueCode: String?,

        @Query("osVersion") osVersion: String? = Build.VERSION.RELEASE,
        @Query("type") type: String? = Constants.TYPE_OWNER,
        @Query("os") os: String? = Constants.DEVICE_ANDROID
    ): Flowable<Response<User>>



    /**
     * 获取 微信 AccessToken
     * @param code APP获取到的微信授权的code
     */
    @GET("$API_ACCOUNT_URI/wxpay/getAccessToken")
    fun getWechatAccessToken(
        @Query("code") code: String?
    ): Flowable<Response<JsonObject>>




    /**
     * 密码登录
     * @Field
     */
    @POST("$API_ACCOUNT_URI/login")
    fun passwordLogin(
        @Query("username") username: String?,
        @Query("passwd") passwd: String?,
        @Query("deviceId") deviceId: String?,
        @Query("platform") platform: String? = Constants.FROM_TYPE_ANDROID
    ): Flowable<Response<User>>

    /**
     * 发送验证码
     * @Field
     */
    @POST("$API_ACCOUNT_URI/sendvc")
    fun sendCaptchaToTarget(@Query("username") username: String?): Flowable<Response<JsonElement>>


    /**
     * 校验验证码
     * @Field
     */
    @POST("$API_ACCOUNT_URI/checkvc")
    fun checkCaptcha(
        @Query("username") username: String?,
        @Query("vc") vc: String?
    ): Flowable<Response<JsonElement>>


    /**
     * 微信注册或登录
     */
    @POST("$API_ACCOUNT_URI/registwx")
    @FormUrlEncoded
    fun doWechatLoginOrRegister(
        @Field("headUrl") headUrl: String?,
        @Field("nickName") nickName: String?,
        @Field("openid") openid: String?,
        @Field("phone") phone: String?,
        @Field("city") city: String?,
        @Field("lat") lat: String?,
        @Field("lng") lng: String?,
        @Query("vc") vc: String?,
        @Query("deviceId") deviceId: String?,

        @Query("platform") platform: String? = Constants.FROM_TYPE_ANDROID
    ): Flowable<Response<User>>


}