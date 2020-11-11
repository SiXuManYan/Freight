package com.fatcloud.account.ui.tests.question

import com.fatcloud.account.frames.entity.question.QuestionChild
import com.sugar.library.frames.network.response.BaseTaskView

/**
 * Created by Wangsw on 2020/9/27 0027 11:03.
 * </br>
 *  测试题
 */
interface TestQuestionView : BaseTaskView {
    abstract fun bindInfo(items: ArrayList<QuestionChild>)
    abstract fun submitSuccess()
}