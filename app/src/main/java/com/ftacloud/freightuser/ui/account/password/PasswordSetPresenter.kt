package com.ftacloud.freightuser.ui.account.password

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.freightuser.frames.backstage.DataServiceFaker
import com.ftacloud.freightuser.frames.network.ApiService
import com.ftacloud.freightuser.storage.CloudDataBase
import com.ftacloud.freightuser.storage.entity.User

import com.google.gson.JsonElement
import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.frames.network.response.BasePresenter
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import com.sugar.library.util.AndroidUtil
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/6/2 0002 15:55.
 * </br>
 *
 */
class PasswordSetPresenter @Inject constructor(private var passwordSetView: PasswordSetView) : BasePresenter(passwordSetView) {


    protected lateinit var apiService: ApiService @Inject set

    protected lateinit var appContext: Context @Inject set

    lateinit var database: CloudDataBase @Inject set

/*
    {
        "code": "200",
        "msg": "成功",
        "data": {
        "token": "2ecd511fb1f143ad922679e73e08fc18",
        "username": "17640339671",
        "nickName": "Fta:笔张"
            }
    }
*/

    /**
     * 注册
     */
    fun register(lifecycleOwner: LifecycleOwner, passWord: String, account: String, captcha: String) {

/*
        requestApi(lifecycleOwner, Lifecycle.Event.ON_DESTROY,
            apiService.register(
                account,
                AndroidUtil.md5(passWord),
                captcha,
                "Android",
                CommonUtils.getLocationInfo()[2],
                CommonUtils.getLocationInfo()[0],
                CommonUtils.getLocationInfo()[1],
                CommonUtils.getShareDefault().getString(Constants.SP_PUSH_DEVICE_ID)
            ),
            object : BaseHttpSubscriber<User>(passwordSetView) {
                override fun onSuccess(data: User?) {
                    data?.let {

                        loginSuccess(it, account)

                        passwordSetView.registerSuccess()
                    }

                }
            })*/
    }


    private fun loginSuccess(it: User, account: String) {
        // 更新用户
        database.userDao().addUser(it)
        User.update()

        // 更新登录状态
        CommonUtils.getShareDefault().put(Constants.SP_LOGIN, true)
        CommonUtils.getShareDefault().put(Constants.SP_TOKEN, it.token)
        CommonUtils.getShareDefault().put(Constants.SP_LAST_LOGIN_USER, account)

        // 刷新页面登录状态
        RxBus.post(Event(Constants.EVENT_LOGIN))
        RxBus.post(Event(Constants.EVENT_NEED_REFRESH))

        // 更新应用数据
        DataServiceFaker.startService(appContext, Constants.ACTION_SYNC_OTHER)
    }

    /**
     * 重设密码
     */
    fun resetPassword(lifecycleOwner: LifecycleOwner, passWord: String, account: String, captcha: String) {
       /* requestApi(lifecycleOwner, Lifecycle.Event.ON_DESTROY,
            apiService.resetPassword(
                account,
                AndroidUtil.md5(passWord),
                captcha
            ),
            object : BaseHttpSubscriber<JsonElement>(passwordSetView) {
                override fun onSuccess(data: JsonElement?) {
                    RxBus.post(Event(Constants.EVENT_PASSWORD_RESET_SUCCESS))
                    passwordSetView.passwordResetSuccess()

                }
            })*/
    }


}