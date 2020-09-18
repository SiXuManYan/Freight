package com.ftacloud.freightuser.ui.account.password.login


import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.freightuser.frames.backstage.DataServiceFaker
import com.ftacloud.freightuser.frames.network.ApiService
import com.ftacloud.freightuser.storage.CloudDataBase
import com.ftacloud.freightuser.storage.entity.User
import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.frames.network.response.LibraryBasePresenter
import com.sugar.library.frames.network.subscriber.LibraryBaseHttpSubscriber
import com.sugar.library.util.AndroidUtil
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/6/2 0002 18:09.
 * </br>
 *
 */
class PasswordLoginPresenterLibrary @Inject constructor(private var view: PasswordLoginViewLibrary) : LibraryBasePresenter(view) {

    protected lateinit var apiService: ApiService @Inject set
    lateinit var database: CloudDataBase @Inject set
    protected lateinit var appContext: Context @Inject set

    fun passwordLogin(lifecycleOwner: LifecycleOwner, currentAccount: String, password: String) {
        val deviceId = CommonUtils.getShareDefault().getString(Constants.SP_PUSH_DEVICE_ID)

        requestApi(lifecycleOwner, Lifecycle.Event.ON_DESTROY,
            apiService.passwordLogin(currentAccount, AndroidUtil.md5(password), deviceId), object : LibraryBaseHttpSubscriber<User>(view) {
                override fun onSuccess(data: User?) {

                    data?.let {
                        loginSuccess(it, currentAccount)
                        view.loginSuccess()
                    }
                }

            }
        )
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


}