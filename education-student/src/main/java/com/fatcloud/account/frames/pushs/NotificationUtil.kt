package com.fatcloud.account.frames.pushs

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.alibaba.sdk.android.push.CloudPushService
import com.alibaba.sdk.android.push.CommonCallback
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory
import com.blankj.utilcode.util.LogUtils
import com.fatcloud.account.BuildConfig
import com.fatcloud.account.R
import com.fatcloud.account.storage.entity.User
import com.fatcloud.account.ui.account.WelcomeActivity
import com.fatcloud.account.ui.account.login.LoginActivity
import com.fatcloud.account.ui.message.MessageActivity

import com.google.gson.Gson
import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants

/**
 * Created by Wangsw on 2020/7/28 0028 14:31.
 * </br>
 *
 */
object NotificationUtil {


    /**
     * NotifyId 兼容。
     * 接口返回 notifyId 大于Int 最大值时，使用此ID
     */
    private var nativeNotifyId = 0



    private const val CHANNEL_ID_NEWS = "channel_id_news"
    private const val CHANNEL_NAME_NEWS = "消息相关通知"

    private const val CHANNEL_ID_OTHER = "channel_id_other"
    private const val CHANNEL_NAME_OTHER = "其他通知"

    // extra map
    private const val pushType = "pushType"
    private const val news = "news"
    private const val order = "order"
    private const val ALIYUN_NOTIFICATION_ID = "_ALIYUN_NOTIFICATION_ID_"
    private const val ALIYUN_NOTIFICATION_PRIORITY = "_ALIYUN_NOTIFICATION_PRIORITY_"


    fun initCloudChannel(applicationContext: Context) {

//        if (BuildConfig.FLAVOR == "dev") {
//            return
//        }

        try {

            PushServiceFactory.init(applicationContext) // crash
            val pushService: CloudPushService = PushServiceFactory.getCloudPushService()
            pushService.register(applicationContext, object : CommonCallback {
                override fun onSuccess(p0: String?) {
                    val deviceId = pushService.deviceId
                    Log.d("推送拦截器", "init cloudchannel success ，deviceId ==>" + deviceId)
                    CommonUtils.getShareStudent().put(Constants.SP_PUSH_DEVICE_ID, deviceId)
                    RxBus.post(Event(Constants.EVENT_UPDATE_PUSH_DEVICE_ID,deviceId))
                }

                override fun onFailed(errorCode: String?, errorMessage: String?) {
                    Log.d("推送拦截器", "init cloudchannel failed -- errorcode:$errorCode -- errorMessage:$errorMessage");
                }
            })

        } catch (e: Exception) {

            LogUtils.d("推送拦截器", "crash == " + e.printStackTrace());

        }


    }

    /**
     * @param title 标题
     * @param summary 内容概要
     *
     *
     */
    fun handlePush(context: Context, title: String, summary: String, extraMap: MutableMap<String, String>) {


//        if (!extraMap.containsKey(pushType)) {
//            return
//        }


        val notifyTitle: String? = title
        val notifyMessage: String? = summary

        var notifyId: Long = 0
        var notifyIntent: Intent? = null

        var defChannelId = CHANNEL_ID_OTHER
        var defChannelName = CHANNEL_NAME_OTHER


        try {

            if (extraMap.containsKey(ALIYUN_NOTIFICATION_ID)) {
                notifyId = extraMap[ALIYUN_NOTIFICATION_ID]!!.toLong()
            }

            defChannelId = CHANNEL_ID_NEWS
            defChannelName = CHANNEL_NAME_NEWS
            notifyIntent = Intent(context, MessageActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

            if (!User.isLogon()) {
                context.startActivity(Intent(context.applicationContext, LoginActivity::class.java))
                return
            }

            notifyIntent?.let {
                showNotification(context, it, defChannelId, defChannelName, notifyTitle, notifyMessage, notifyId)
            }

        } catch (e: Exception) {
            Log.e("推送拦截器", "json 解析失败 exception :$e")
        }
    }


    /**
     * 发送通知
     * 8.0以上会创建通知渠道
     *
     * @param context
     * @param intent
     * @param channelId   通知渠道id
     * @param channelName 通知渠道说明（用户可见）
     * @param title
     * @param contentText
     * @param notifyId    通知类型
     */
    private fun showNotification(
        context: Context, intent: Intent, channelId: String,
        channelName: String, title: String?, contentText: String?, notifyId: Long
    ) {

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // 创建通知渠道
            builder = NotificationCompat.Builder(context, channelId)

            // 在这里可以设置 channel 的重要程度，(如:拿channelId来衡量)
            val defImportance = NotificationManager.IMPORTANCE_DEFAULT
            createNotificationChannel(context, channelId, channelName, defImportance)
        } else {
            builder = NotificationCompat.Builder(context, "")
        }

        builder.setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE)


        // 默认配置
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        val notification = builder.setContentTitle(title)
            .setStyle(NotificationCompat.BigTextStyle())
            .setContentText(contentText)
            .setWhen(System.currentTimeMillis())
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()


        val notificationId = if (notifyId > Int.MAX_VALUE) {
            nativeNotifyId++
        } else {
            notifyId.toInt()
        }
        manager.notify(notificationId, notification)
    }


    /**
     * 创建通知渠道
     *
     * @param context
     * @param channelId   渠道id
     * @param channelName 渠道描述(用户可见)
     * @param importance  重要性
     */
    @TargetApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(context: Context, channelId: String, channelName: String, importance: Int) {

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(channelId, channelName, importance)
        manager.createNotificationChannel(channel)
    }


}