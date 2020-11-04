package com.ftacloud.student.frames.network

import com.ftacloud.student.frames.entity.*
import com.ftacloud.student.frames.entity.question.Question
import com.ftacloud.student.frames.entity.question.QuestionChild
import com.ftacloud.student.frames.entity.request.*
import com.ftacloud.student.storage.entity.User
import com.google.gson.JsonArray
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
     * 体验课详情
     */
    @POST("/api/student/schedule/getExperienceInfo")
    fun getCourseDetail(@Body json: CourseDetailRequest): Flowable<Response<CourseDetail>>


    /**
     * 预约体验课
     */
    @POST("/api/student/schedule/bookingExperience")
    fun bookingExperience(@Body json: CourseDetailRequest): Flowable<Response<JsonObject>>


    /**
     * 普通课详情
     */
    @POST("/api/student/schedule/getInfo")
    fun getCommonCourseDetail(@Body json: CourseDetailRequest): Flowable<Response<CourseDetail>>

    /**
     * 我的消息列表
     */
    @POST("/api/student/message/list")
    fun getMessageList(@Body json: ListRequest): Flowable<Response<JsonArray>>


    /**
     * 全部订单
     */
    @POST("/api/student/order/list")
    fun getOrderList(@Body json: ListRequest): Flowable<Response<JsonArray>>

    /**
     * 待支付订单
     */
    @POST("/api/student/order/listUnpaid")
    fun getOrderListUnpaid(@Body json: ListRequest): Flowable<Response<JsonArray>>

    /**
     * 已支付订单
     */
    @POST("/api/student/order/listPaid")
    fun getOrderListPaid(@Body json: ListRequest): Flowable<Response<JsonArray>>

    /**
     * 课后任务
     */
    @POST("/api/student/task/list")
    fun getTaskList(@Body json: ListRequest): Flowable<Response<JsonArray>>

    /**
     * 获取课后任务信息
     */
    @POST("/api/student/task/get")
    fun getTaskDetail(@Body json: TaskDetailRequest): Flowable<Response<TaskDetail>>

    /**
     * 预约体验课
     */
    @POST("/api/student/schedule/bookingExperience")
    fun reserveCourse(@Body json: TaskDetailRequest): Flowable<Response<JsonObject>>

    /**
     *  公共接口
     *
     */
    @GET("/api/student/common/")
    fun reserveCourse(): Flowable<Response<AppCommon>>


    /**
     * 获取测验题
     */
    @POST("/api/student/quizzes/get")
    fun getQuestion(@Body json: GetQuestionRequest): Flowable<Response<Question>>

    /**
     * 提交测验题
     */
    @POST("/api/student/quizzes/submit")
    fun submitQuestion(@Body json: SubmitQuestionRequest): Flowable<Response<JsonObject>>

    /**
     * 我的课程(未开课)
     */
    @POST("/api/student/schedule/listUnTeach")
    fun unTeachCourse(@Body json: ListRequest): Flowable<Response<JsonArray>>

    /**
     * 我的课程(已开课)
     */
    @POST("/api/student/schedule/listTaught")
    fun listTaught(@Body json: ListRequest): Flowable<Response<JsonArray>>


    /**
     * 我的课程(已开课)
     */
    @POST("/api/student/quizzes/getResult")
    fun getQuestionResult(@Body json: QuestionResultRequest): Flowable<Response<JsonObject>>


   /**
     * 正式课程信息
     */
    @POST("/api/student/schedule/getInfo")
    fun getFormalCourseDetail(@Body json: FormalCourseDetailRequest): Flowable<Response<FormalCourseDetail>>




}