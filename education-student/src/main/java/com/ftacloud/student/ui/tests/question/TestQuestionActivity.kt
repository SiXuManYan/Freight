package com.ftacloud.student.ui.tests.question

import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity

/**
 * Created by Wangsw on 2020/9/27 0027 11:26.
 * </br>
 * 测试题
 */
class TestQuestionActivity : BaseMVPActivity<TestQuestionPresenter>(),TestQuestionView {

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun getLayoutId() = R.layout.activity_test_question

    override fun initViews() {
        TODO("Not yet implemented")
    }


}