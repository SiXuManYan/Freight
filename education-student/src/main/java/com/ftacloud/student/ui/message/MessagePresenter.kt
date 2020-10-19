package com.ftacloud.student.ui.message

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.frames.network.response.BasePresenter
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/25 0025 14:00.
 * </br>
 *
 */
class MessagePresenter @Inject constructor(private var view: MessageView) : BasePresenter(view) {



    override fun loadList(lifecycle: LifecycleOwner, page: Int, pageSize: Int, lastItemId: String?) {


        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            apiService.getMessage(), object : BaseHttpSubscriber<JsonObject>(view) {
                override fun onSuccess(data: JsonObject?) {
                    ToastUtils.showShort("设置成功")
                }

                override fun onError(e: Throwable) {
                    ToastUtils.showShort("设置失败")
                }

            }
        )

    }



}