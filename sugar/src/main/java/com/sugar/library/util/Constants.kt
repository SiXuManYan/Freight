package com.sugar.library.util

object Constants {

    const val PR_DEFAULT = "cloud"
    const val PR_LOCATION = "location"

    const val SP_DEV_URL = "dev_url"
    const val SP_LOGIN = "login"
    const val SP_TOKEN = "token"
    const val SP_DATA = "data"
    const val SP_LOCAL_CODE = "local_code"
    const val SP_LOCAL_NAME = "local_name"
    const val SP_SELECT_LOCAL_CODE = "select_code"
    const val SP_SELECT_LOCAL_NAME = "select_name"
    const val SP_LONGITUDE = "longitude"
    const val SP_LATITUDE = "latitude"
    const val SP_ADDRESS = "address"
    const val SP_NOVICE = "novice"
    const val SP_IS_SHOW_USER_AGREEMENT = "sp_is_show_user_agreement"// 是否展示过用户协议
    const val SP_OPERATING_NOTIFICATION_SWITCH = "sp_check_notification_switch"// 用户是否忽略检查通知权限开关


    /** 定位间隔 */
    const val LOCATION_INTERVAL = 300000L

    /** 防连点时间间隔 **/
    const val CLICK_INTERVAL = 1000L

    /** WebView 参数 */
    const val WEB_TITLE = "web_title"
    const val WEB_URL = "web_url"


    const val WEB_STYLE = "<style>* {font-size:14px;line-height:20px;} p {color:#333;} a {color:#3E62A6;} " +
            "img {max-width:100%;} img.alignleft {float:left;max-width:120px;margin:0 10px 5px 0;border:1px solid #ccc;background:#fff;padding:2px;} " +
            "pre {font-size:9pt;line-height:12pt;font-family:Courier New,Arial;border:1px solid #ddd;border-left:5px solid #6CE26C;background:#f6f6f6;padding:5px;overflow: auto;} " +
            "a.tag {font-size:15px;text-decoration:none;background-color:#bbd6f3;border-bottom:2px solid #3E6D8E;border-right:2px solid #7F9FB6;color:#284a7b;margin:2px 2px 2px 0;padding:2px 4px;white-space:nowrap;}</style>"

    const val IMAGE_URL_FORMAT = "Android_%1s"

    const val DEVICE_ANDROID = "1"


    const val PARAM_TITLE = "param_title"

    const val PARAM_URL = "param_url"
    const val PARAM_HANDLE_BACK = "param_handle_back" // 处理返回键
    const val PARAM_WEB_REFRESH = "param_web_refresh" // 是否可刷新
    const val PARAM_WEB_LOAD_LOCAL_HTML = "param_web_load_local_html"
    const val PARAM_WEB_CHANGETITLE = "param_web_changetitle" // 自动改变标题


    /** RxBus Event */
    const val EVENT_FINISH_ALL = 0x60


    // 列表数据 key
    const val KEY_DATA = "data"


    /**
     * 应用初始化数据
     */
    const val ACTION_SYNC = 1
    const val ACTION_SYNC_OTHER = 2
    const val ACTION_DATA_WORK = "action"


}