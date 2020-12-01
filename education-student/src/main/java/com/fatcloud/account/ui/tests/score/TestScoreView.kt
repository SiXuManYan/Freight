package com.fatcloud.account.ui.tests.score

import com.fatcloud.account.frames.entity.TestScore
import com.sugar.library.frames.network.response.BaseView

/**
 * Created by Wangsw on 2020/9/27 0027 20:07.
 * </br>
 *
 */
interface TestScoreView : BaseView {
    fun bindView(data: TestScore)
}