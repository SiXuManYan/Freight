package com.ftacloud.student.frames.network

import com.ftacloud.student.BuildConfig
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants


object UrlUtil {

    fun getdevUrlIndex(): Int {
        var index = 0
        for (i in URL_LIST.indices) {
            if (URL_LIST[i].second.first == BuildConfig.SERVER_HOST) {
                index = i
                break
            }
        }
        return CommonUtils.getShareStudent().getInt(Constants.SP_DEV_URL, index)
    }

    fun setdevUrlIndex(index: Int) {
        CommonUtils.getShareStudent().put(Constants.SP_DEV_URL, index)
    }


    val SERVER_HOST by lazy {
        if (BuildConfig.FLAVOR == "dev") {
            URL_LIST[getdevUrlIndex()].second.first
        } else {
            BuildConfig.SERVER_HOST
        }

    }

    val H5_BASE_URL by lazy {
        if (BuildConfig.FLAVOR == "dev") {
            URL_LIST[getdevUrlIndex()].second.second
        } else {
            BuildConfig.H5_BASE_URL
        }
    }
    val URL_LIST =

        listOf(
            Pair("开发服", Pair("http://192.168.1.183:52001", "https://www.baidu.com")),
            Pair("正式服", Pair("https://api-cloudaccount.ftacloud.com", "https://www.baidu.com"))
        )
}