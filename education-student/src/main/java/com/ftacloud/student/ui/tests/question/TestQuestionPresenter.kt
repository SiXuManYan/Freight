package com.ftacloud.student.ui.tests.question

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.entity.question.Question
import com.ftacloud.student.frames.entity.request.GetQuestionRequest
import com.ftacloud.student.frames.network.response.BasePresenter
import com.ftacloud.student.storage.entity.User
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/27 0027 11:26.
 * </br>
 *
 */
class TestQuestionPresenter @Inject constructor(private var view: TestQuestionView) : BasePresenter(view) {


    fun loadUserInfo(lifecycleOwner: LifecycleOwner, id: String) {


        val apply = GetQuestionRequest().apply {
            quizzesId = id

        }

        requestApi(lifecycleOwner, Lifecycle.Event.ON_DESTROY, apiService.getQuestion(apply), object : BaseHttpSubscriber<Question>(view) {

            override fun onSuccess(data: Question?) {
                if (data == null) {
                    return
                }
                view.bindInfo(data.items)



            }
        })

    }


}