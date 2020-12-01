package com.fatcloud.account.ui.tests.score

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fatcloud.account.frames.entity.TestScore
import com.fatcloud.account.frames.entity.request.QuestionResultRequest
import com.fatcloud.account.frames.network.response.BasePresenter
import com.google.gson.JsonObject
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/27 0027 20:09.
 * </br>
 *
 */
class TestScorePresenter @Inject constructor(private var view: TestScoreView) : BasePresenter(view) {

    fun getQuestionResult(lifecycleOwner: LifecycleOwner, id: String) {


        val apply = QuestionResultRequest().apply {
            quizzesOfStudentId = id

        }
        requestApi(lifecycleOwner, Lifecycle.Event.ON_DESTROY, apiService.getQuestionResult(apply), object : BaseHttpSubscriber<TestScore>(view) {

            override fun onSuccess(data: TestScore?) {
                if (data == null) {
                    return
                }
                view.bindView(data)

            }
        })

    }


}