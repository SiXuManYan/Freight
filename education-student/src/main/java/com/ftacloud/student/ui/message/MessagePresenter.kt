package com.ftacloud.student.ui.message

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.frames.entity.request.MessageRequest
import com.ftacloud.student.frames.network.response.BasePresenter
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

        val apply = MessageRequest().apply {
            lastId = lastItemId
            size = pageSize
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            apiService.getMessageList(apply), object : BaseHttpSubscriber<JsonObject>(view) {
                override fun onSuccess(data: JsonObject?) {
                    ToastUtils.showShort("列表请求成功")
                }

                override fun onError(e: Throwable) {
                    ToastUtils.showShort("列表请求失败")
                }

            }
        )

    }


}