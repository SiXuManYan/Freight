package com.ftacloud.student.ui.course.schedule.detail

import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity

/**
 * Created by Wangsw on 2020/9/29 0029 11:15.
 * </br>
 *  课程表详情，未开课
 */
class NoClassActivity : BaseMVPActivity<NoClassPresenter>(), NoClassView {


    override fun getLayoutId() = R.layout.activity_no_class

    override fun initViews() {

    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun hideLoading() = dismissLoadingDialog()
}