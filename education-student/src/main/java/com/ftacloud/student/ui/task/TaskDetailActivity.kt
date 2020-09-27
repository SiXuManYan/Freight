package com.ftacloud.student.ui.task

import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity

/**
 * Created by Wangsw on 2020/9/27 0027 13:43.
 * </br>
 *  课后任务详情
 */
class TaskDetailActivity : BaseMVPActivity<TaskDetailPresenter>(), TaskDetailView {

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun getLayoutId() = R.layout.activity_after_class_task

    override fun initViews() {


    }


}