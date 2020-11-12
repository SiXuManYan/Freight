package com.fatcloud.account.ui.tests.score

import com.fatcloud.account.R
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants

/**
 * Created by Wangsw on 2020/9/27 0027 20:09.
 * </br>
 *  评分详情
 */
class TestScoreActivity : BaseMVPActivity<TestScorePresenter>(), TestScoreView {

    private var quizzesOfStudentId = ""

    override fun getLayoutId() = R.layout.test_score

    override fun initPadLayout() {
        CommonUtils.setStatusBarTransparentWithLightMode(this)
    }


    override fun initViews() {

        if (intent.extras == null || !intent.extras!!.containsKey(Constants.PARAM_ID) || !intent.extras!!.containsKey(Constants.PARAM_STUDENT_ID)) {
            finish()
            return
        }
        quizzesOfStudentId = intent.extras!!.getString(Constants.PARAM_STUDENT_ID, "")
        presenter.getQuestionResult(this, quizzesOfStudentId)
    }


}