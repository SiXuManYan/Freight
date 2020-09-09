package com.ftacloud.freightuser.network


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



    }


}