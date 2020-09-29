package com.ftacloud.student.ui.settings

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.network.response.BasePresenter
import com.ftacloud.student.storage.entity.User
import com.google.gson.JsonElement
import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.frames.network.response.LibraryBasePresenter
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/21 0021 17:45.
 * </br>
 *
 */
class SettingPresenter @Inject constructor(private var view: SettingView) : BasePresenter(view) {


    fun loginOutRequest(lifecycleOwner: LifecycleOwner) {

        requestApi(lifecycleOwner, Lifecycle.Event.ON_DESTROY, apiService.logout(), object : BaseHttpSubscriber<JsonElement>(view, false) {


                override fun onSuccess(data: JsonElement?) {
                    removeUserInfo()
                }


                override fun onError(e: Throwable) {
                    removeUserInfo()
                }
            })

    }


    private fun removeUserInfo() {
        // 清空用户信息
        database.userDao().clear()
        User.clearAll()

        // 更新登录状态
        CommonUtils.getShareStudent().put(Constants.SP_LOGIN, false)
        CommonUtils.getShareDefault().put(Constants.SP_TOKEN, "")

        // 更新应用内开关设置
        CommonUtils.getShareDefault().put(Constants.SP_OPERATING_NOTIFICATION_SWITCH, false)

        // 刷新页面登录状态
        RxBus.post(Event(Constants.EVENT_NEED_REFRESH))
        RxBus.post(Event(Constants.EVENT_LOGOUT))
        view.loginOutSuccess()
    }


}