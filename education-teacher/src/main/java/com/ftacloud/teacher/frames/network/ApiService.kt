package com.ftacloud.teacher.frames.network



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




}