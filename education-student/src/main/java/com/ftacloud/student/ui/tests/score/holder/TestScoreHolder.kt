package com.ftacloud.student.ui.tests.score.holder

import android.view.View
import android.view.ViewGroup
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.MyTest
import com.ftacloud.student.frames.entity.TestScore
import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer

/**
 * Created by Wangsw on 2020/9/27 0027 19:17.
 * </br>
 *  我的测评
 */
class TestScoreHolder(parent: ViewGroup?) : BaseItemViewHolder<TestScore>(parent, R.layout.item_test_score), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: TestScore?) {
        if (data == null) {
            return
        }


    }

}