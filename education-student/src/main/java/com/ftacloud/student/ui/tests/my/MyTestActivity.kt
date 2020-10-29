package com.ftacloud.student.ui.tests.my

import android.os.Bundle
import android.view.View
import butterknife.OnClick
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.storage.entity.User
import com.ftacloud.student.ui.tests.question.TestQuestionActivity
import com.ftacloud.student.ui.tests.score.TestScoreActivity
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants

/**
 * Created by Wangsw on 2020/9/27 0027 19:13.
 * </br>
 * 我的测评
 *
 */
class MyTestActivity : BaseMVPActivity< MyTestPresenter>(), MyTestView {

    override fun getLayoutId() = R.layout.activity_my_test

    override fun initViews() {

    }


/*

            // 评分详情
            startActivity(TestScoreActivity::class.java)

            // 测试题详情
            startActivity(TestQuestionActivity::class.java)
*/




        @OnClick(
                R.id.test_sore_cv,
                R.id.test_cv
            )
            fun onClick(view: View) {
                if (CommonUtils.isDoubleClick(view)) {
                    return
                }
                when (view.id) {
                    R.id.test_sore_cv -> {
                        startActivity(TestScoreActivity::class.java, Bundle().apply {
                            putString(Constants.PARAM_STUDENT_ID, User.get().id.toString())
                        })
                    }
                    R.id.test_cv -> {
                        startActivity(TestQuestionActivity::class.java)
                    }
                    else -> {
                    }
                }
            }

}