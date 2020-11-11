package com.fatcloud.account.frames.network.response

import android.content.Context
import com.fatcloud.account.frames.backstage.DataServiceFaker
import com.fatcloud.account.frames.network.ApiService
import com.fatcloud.account.storage.CloudDataBase
import com.fatcloud.account.storage.entity.User
import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.frames.network.response.LibraryBasePresenter
import com.sugar.library.frames.network.response.BaseView
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/10 0010 13:55.
 * </br>
 *
 */
open class BasePresenter constructor(private var view: BaseView?) : LibraryBasePresenter(view) {

    protected lateinit var apiService: ApiService @Inject set

    protected lateinit var appContext: Context @Inject set

    lateinit var database: CloudDataBase @Inject set


    fun loginSuccess(it: User, account: String) {

        database.userDao().addUser(it)
        User.update()

        // 更新登录状态
        val shareStudent = CommonUtils.getShareStudent()

        shareStudent.put(Constants.SP_TOKEN, it.token)
        shareStudent.put(Constants.SP_LAST_LOGIN_USER, account)
        shareStudent.put(Constants.SP_LOGIN, true)

        // 更新用户


        // 更新应用数据
        DataServiceFaker.startService(appContext, Constants.ACTION_SYNC_OTHER)

        // 刷新页面登录状态
        RxBus.post(Event(Constants.EVENT_LOGIN))
        RxBus.post(Event(Constants.EVENT_NEED_REFRESH))

    }


}