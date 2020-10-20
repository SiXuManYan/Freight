package com.ftacloud.student.frames.network

import com.ftacloud.student.frames.entity.CourseDetail
import com.ftacloud.student.frames.entity.SecurityTokenModel
import com.ftacloud.student.frames.entity.request.*
import com.ftacloud.student.storage.entity.User
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.sugar.library.frames.network.Response
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


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

        private const val API_SMS_URI = "api/sms"
        private const val API_ACCOUNT_URI = "api/account"
        private const val API_STUDENT_URI = "api/student"

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
    fun loginFast(@Body module: FastLogin?): Flowable<Response<User>>


    /**
     * 注册
     */
    @POST("$API_ACCOUNT_URI/registry")
    fun register(@Body module: Register?): Flowable<Response<User>>


    /**
     * 密码登陆
     *
     */
    @POST("$API_ACCOUNT_URI/login")
    fun loginPassword(@Body module: PasswordLogin?): Flowable<Response<User>>


    /**
     * 设置密码(找回密码)
     *
     */
    @POST("$API_ACCOUNT_URI/setPasswd")
    fun setPassword(@Body module: SetPassword?): Flowable<Response<User>>

    /**
     * 获取用户信息
     *
     */
    @POST("$API_ACCOUNT_URI/info")
    fun requestUserInfo(): Flowable<Response<User>>

    /**
     * 退出登陆
     *
     */
    @POST("$API_ACCOUNT_URI/logout")
    fun logout(): Flowable<Response<JsonElement>>


    /**
     * 获取资源上传 oss token
     *
     */
    @POST("get/alioss/stsToken")
    fun getOssSecurityToken(): Flowable<Response<SecurityTokenModel>>

    /**
     * 设置用户信息
     *
     */
    @POST("$API_ACCOUNT_URI/setInfo")
    fun setUserInfo(@Body json: SetUserInfo): Flowable<Response<JsonElement>>

    /**
     * 获取首页信息
     */
    @GET("/api/student/home/")
    fun getHomeInfo(): Flowable<Response<JsonObject>>

    /**
     * 我的消息列表
     */
    @POST("/api/student/message/list")
    fun getMessageList(@Body json: MessageRequest): Flowable<Response<JsonObject>>


    /**
     * 课程详情
     */
    @POST("/api/student/schedule/get")
    fun getCourseDetail(@Body json: CourseDetailRequest): Flowable<Response<CourseDetail>>


}