package com.ftacloud.student.common

import android.app.Activity
import android.content.Context
import com.blankj.utilcode.util.RegexUtils
import com.ftacloud.student.ui.app.CloudAccountApplication
import com.sugar.library.BuildConfig


/**
 * Created by Wangsw on 2020/10/9 0009 10:58.
 * </br>
 *
 */
object OssUtil {


    /**
     * 是否为加密url
     */
    fun isOssSignUrl(url: String): Boolean {
        if (url.contains(BuildConfig.OSS_PRIVATE_BUCKET_NAME) || !RegexUtils.isURL(url)) {
            return true
        }
        return false
    }


    /**
     * 获取OSS 签名
     */
    fun getRealOssUrl(context: Context?, url: String, ossCallBack: CloudAccountApplication.OssSignCallBack) {

        if (!isOssSignUrl(url)) {
            ossCallBack.ossUrlSignEnd(url)
            return
        }

        val activity = context as Activity
        val application = activity.application as CloudAccountApplication

        var newUrl = ""
        val isStart = url.startsWith("/", true)
        newUrl = if (isStart) {
            url.replaceFirst("/", "")
        } else {
            url
        }
        application.getOssSecurityTokenForSignUrl(getOssSignUrlObjectKey(newUrl), ossCallBack)
    }


    /**
     * https://ftacloud-bucket-private.oss-cn-qingdao.aliyuncs.com/android/dev/image/encryption/image_1592462141234.jpg
     * oss-cn-qingdao.aliyuncs.com
     * 获取加密图片的 Oss object key
     */
    fun getOssSignUrlObjectKey(url: String): String {

        return if (url.contains(BuildConfig.OSS_PRIVATE_BUCKET_NAME)) {
            url.replace(BuildConfig.OSS_PRIVATE_BUCKET_NAME, "")
        } else {
            url
        }


    }

}