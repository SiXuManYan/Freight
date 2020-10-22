package com.ftacloud.student

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.network.response.BasePresenter
import com.ftacloud.student.storage.entity.User
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



}