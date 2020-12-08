package com.fatcloud.account.ui.tests.my

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fatcloud.account.frames.entity.Message
import com.fatcloud.account.frames.entity.question.my.MyQuestion
import com.fatcloud.account.frames.entity.request.ListRequest
import com.fatcloud.account.frames.network.response.BasePresenter
import com.google.gson.JsonArray
import com.sugar.library.frames.network.subscriber.BaseJsonArrayHttpSubscriber
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/27 0027 19:17.
 * </br>
 *
 */
class MyTestPresenter @Inject constructor(private var view: MyTestView) : BasePresenter(view) {

    override fun loadList(lifecycle: LifecycleOwner, page: Int, pageSize: Int, lastItemId: String?) {

        val apply = ListRequest().apply {
            lastId = lastItemId
            size = pageSize
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,
            apiService.getQuestionResultList(apply), object : BaseJsonArrayHttpSubscriber<MyQuestion>(view) {

                override fun onSuccess(jsonArray: JsonArray?, list: ArrayList<MyQuestion>, lastItemId: String?) {
                    view.bindList(list, lastItemId)
                }

            }
        )

    }

}