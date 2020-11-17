package com.fatcloud.account.frames.pushs

/**
 * Created by Wangsw on 2020/7/28 0028 15:36.
 * </br>
 *
 */
class PushIntentService{

//    : AliyunMessageIntentService() {
  /*  private val R_TAG = "PushIntentService"

    *//**
     * 推送通知的回调方法
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     *//*
    override fun onNotification(context: Context?, title: String, summary: String, extraMap: Map<String?, String?>?) {
        Log.i(R_TAG, "收到一条推送通知 ： $title, summary:$summary")
    }

    *//**
     * 推送消息的回调方法
     * @param context
     * @param cPushMessage
     *//*
    override fun onMessage(context: Context?, cPushMessage: CPushMessage) {
        Log.i(R_TAG, "收到一条推送消息 ： " + cPushMessage.title + ", content:" + cPushMessage.content)
        //  todo   NotificationUtil.handlePush
//        NotificationUtil.handlePush()
    }

    *//**
     * 从通知栏打开通知的扩展处理
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     *//*
    override fun onNotificationOpened(context: Context?, title: String, summary: String, extraMap: String) {
        Log.i(R_TAG, "onNotificationOpened ：  : $title : $summary : $extraMap")
    }

    *//**
     * 无动作通知点击回调。当在后台或阿里云控制台指定的通知动作为无逻辑跳转时,通知点击回调为onNotificationClickedWithNoAction而不是onNotificationOpened
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     *//*
    override fun onNotificationClickedWithNoAction(context: Context?, title: String, summary: String, extraMap: String) {
        Log.i(R_TAG, "onNotificationClickedWithNoAction ：  : $title : $summary : $extraMap")
    }

    *//**
     * 通知删除回调
     * @param context
     * @param messageId
     *//*
    override fun onNotificationRemoved(context: Context?, messageId: String) {
        Log.i(R_TAG, "onNotificationRemoved ： $messageId")
    }

    *//**
     * 应用处于前台时通知到达回调。注意:该方法仅对自定义样式通知有效,相关详情请参考https://help.aliyun.com/document_detail/30066.html#h3-3-4-basiccustompushnotification-api
     * @param context
     * @param title
     * @param summary
     * @param extraMap
     * @param openType
     * @param openActivity
     * @param openUrl
     *//*
    override fun onNotificationReceivedInApp(context: Context?, title: String, summary: String, extraMap: Map<String?, String?>, openType: Int, openActivity: String, openUrl: String) {
        Log.i(R_TAG, "onNotificationReceivedInApp ：  : $title : $summary  $extraMap : $openType : $openActivity : $openUrl")
    }*/
}