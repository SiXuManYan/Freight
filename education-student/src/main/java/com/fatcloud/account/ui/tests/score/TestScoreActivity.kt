package com.fatcloud.account.ui.tests.score

import android.content.DialogInterface
import com.fatcloud.account.R
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.fatcloud.account.frames.entity.TestScore
import com.sugar.library.ui.widget.dialog.AlertDialog
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
        if (intent.extras == null || !intent.extras!!.containsKey(Constants.PARAM_STUDENT_ID)) {
            finish()
            return
        }
        quizzesOfStudentId =   intent.extras!!.getString(Constants.PARAM_STUDENT_ID)!!
//        quizzesOfStudentId = CommonUtils.getShareStudent().getString(Constants.SP_QUIZZES_OF_STUDENT_ID, "")
        presenter.getQuestionResult(this, quizzesOfStudentId)
    }

    override fun bindView(data: TestScore) {

        if (data.stateValue.contains(TestScore.UNSUBMITTED)) {

            AlertDialog.Builder(this)
                .setTitle(R.string.hint)
                .setMessage(getString(R.string.test_not_submit))
                .setCancelable(false)
                .setPositiveButton(R.string.confirm, AlertDialog.STANDARD, DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                    finish()
                })
                .create()
                .show()
            return
        }

    }

}