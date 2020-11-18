package com.fatcloud.account.ui.main

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fatcloud.account.frames.entity.DeviceId
import com.fatcloud.account.frames.network.response.BasePresenter
import com.fatcloud.account.storage.entity.User
import com.google.gson.JsonObject
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/5/25 0025 16:22.
 * </br>
 *
 */
class MainPresenter @Inject constructor(private var view: MainView) : BasePresenter(view) {


    fun loadUserInfo(lifecycleOwner: LifecycleOwner) {

        val boolean = CommonUtils.getShareStudent().getBoolean(Constants.SP_LOGIN)
        if (!boolean) {
            return
        }

        requestApi(lifecycleOwner, Lifecycle.Event.ON_DESTROY, apiService.requestUserInfo(), object : BaseHttpSubscriber<User>(view) {
            override fun onSuccess(data: User?) {
                data?.let {

                }
            }
        })

    }



    fun getUnReadMessageCount(lifecycle: LifecycleOwner) {
        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,
            apiService.getUnReadMessageCount(), object : BaseHttpSubscriber<String>(view) {
                override fun onSuccess(data: String?){
                    data?.let {
                        view.unReadMessageCount(it)
                    }

                }
            }
        )
    }


    fun updatePushDeviceId(lifecycle: LifecycleOwner,deviceId: String){

        val apply = DeviceId().apply {
            pushDeviceId = deviceId
        }
        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,
            apiService.setPushDeviceId(apply), object : BaseHttpSubscriber<JsonObject>(view) {
                override fun onSuccess(data: JsonObject?){
                }
            }
        )
    }



}