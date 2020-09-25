package com.ftacloud.student.ui.account.forget

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.network.request.SetPassword
import com.ftacloud.student.frames.network.response.BasePresenter
import com.ftacloud.student.storage.entity.User
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import com.sugar.library.util.AndroidUtil
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/21 0021 9:37.
 * </br>
 *
 */
class ForgetPresenter @Inject constructor(private var view: ForgetView) : BasePresenter(view) {

    fun retrieve(lifecycle: LifecycleOwner, phoneValue: String, verifyValue: String, passwordValue: String, passwordAgainValue: String) {

        val apply = SetPassword().apply {
            username = phoneValue
            passwd = AndroidUtil.md5(passwordValue)
            vc = verifyValue
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY, apiService.setPassword(apply), object : BaseHttpSubscriber<User>(view) {
            override fun onSuccess(data: User?) {
                data?.let {
                    view.retrieveSuccess()
                }
            }
        })


    }

}