package com.ftacloud.freightuser.ui.account.login.wechat

import android.content.Context
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.freightuser.frames.network.ApiService
import com.google.gson.JsonObject
import com.sugar.library.frames.network.response.LibraryBasePresenter
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/6/18 0018 20:43.
 * </br>
 *
 */
class WechatLoginRegisterPresenterLibrary @Inject constructor(private var wechatLoginRegisterView: WechatLoginRegisterView) :
    LibraryBasePresenter(wechatLoginRegisterView) {


    protected lateinit var apiService: ApiService @Inject set

    protected lateinit var appContext: Context @Inject set

    /**
     * 检查用户是否存在
     */
    fun checkAccountIsExisted(lifecycleOwner: LifecycleOwner, account: String) {

        requestApi(lifecycleOwner, Lifecycle.Event.ON_DESTROY,
            apiService.checkAccountIsExisted(account), object : BaseHttpSubscriber<JsonObject>(wechatLoginRegisterView) {
                override fun onSuccess(data: JsonObject?) {

                    data?.let {
                        if (it.has("existed")) {
                            wechatLoginRegisterView.accountExistedTag(it.get("existed").asBoolean, account)
                        }
                    }
                }
            }
        )
    }


}