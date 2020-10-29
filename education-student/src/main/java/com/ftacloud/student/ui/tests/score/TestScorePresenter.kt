package com.ftacloud.student.ui.tests.score

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.entity.question.Question
import com.ftacloud.student.frames.entity.request.GetQuestionRequest
import com.ftacloud.student.frames.entity.request.QuestionResultRequest
import com.ftacloud.student.frames.network.response.BasePresenter
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
        requestApi(lifecycleOwner, Lifecycle.Event.ON_DESTROY, apiService.getQuestionResult(apply), object : BaseHttpSubscriber<JsonObject>(view) {

            override fun onSuccess(data: JsonObject?) {
                if (data == null) {
                    return
                }

            }
        })

    }


}