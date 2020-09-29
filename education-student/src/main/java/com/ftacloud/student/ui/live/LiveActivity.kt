package com.ftacloud.student.ui.live

import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity

/**
 * Created by Wangsw on 2020/9/29 0029 16:52.
 * </br>
 *
 */
class LiveActivity :BaseMVPActivity<LivePresenter>(),LiveView {
    override fun getLayoutId() = R.layout.activity_live

    override fun initViews() {

    }
}