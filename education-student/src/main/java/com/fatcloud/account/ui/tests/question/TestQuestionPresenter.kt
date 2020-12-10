package com.fatcloud.account.ui.tests.question

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.fatcloud.account.R
import com.fatcloud.account.frames.entity.question.Question
import com.fatcloud.account.frames.entity.question.QuestionChild
import com.fatcloud.account.frames.entity.question.QuestionChildType
import com.fatcloud.account.frames.entity.request.GetQuestionRequest
import com.fatcloud.account.frames.entity.request.SubmitQuestionRequest
import com.fatcloud.account.frames.network.response.BasePresenter
import com.google.gson.JsonObject
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/27 0027 11:26.
 * </br>
 *
 */
class TestQuestionPresenter @Inject constructor(private var view: TestQuestionView) : BasePresenter(view) {

    fun getQuestion(lifecycleOwner: LifecycleOwner, id: String) {


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


    fun submitQuestion(lifecycleOwner: LifecycleOwner, id: String, mAdapter: RecyclerArrayAdapter<QuestionChild>) {

        mAdapter.allData.forEach {
            if (it.itemType.contains(QuestionChildType.SELECT.name) && it.nativeAnswerSelect.isEmpty()) {
                ToastUtils.showShort(StringUtils.getString(R.string.select_right_answer))
                return
            }
            if (it.itemType.contains(QuestionChildType.FILL_IN_THE_BLANKS.name) && it.nativeAnswerFill.isEmpty()) {
                ToastUtils.showShort(StringUtils.getString(R.string.input_answer))
                return
            }
            if (it.itemType.contains(QuestionChildType.SOUND_RECORDING.name) && it.nativeAnswerRecordPath.isEmpty()) {
                ToastUtils.showShort(StringUtils.getString(R.string.upload_record))
                return
            }
        }

        val studentAnswers = ArrayList<String>()

        mAdapter.allData.forEach {

            when {
                it.itemType.contains(QuestionChildType.SELECT.name) -> {
                    studentAnswers.add(it.nativeAnswerSelect)
                }
                it.itemType.contains(QuestionChildType.FILL_IN_THE_BLANKS.name) -> {
                    studentAnswers.add(it.nativeAnswerFill)
                }
                else -> {
                    studentAnswers.add(it.nativeAnswerRecordServerPath)
                }
            }
        }


        val apply = SubmitQuestionRequest().apply {
            quizzesOfStudentId = id
            answers = studentAnswers
        }

        requestApi(lifecycleOwner, Lifecycle.Event.ON_DESTROY, apiService.submitQuestion(apply), object : BaseHttpSubscriber<JsonObject>(view) {

            override fun onSuccess(data: JsonObject?) {
                view.submitSuccess()
            }
        })

    }


}