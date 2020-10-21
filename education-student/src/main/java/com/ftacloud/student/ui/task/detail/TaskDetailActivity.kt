package com.ftacloud.student.ui.task.detail

import android.view.View
import butterknife.OnClick
import com.ftacloud.student.R
import com.ftacloud.student.common.StudentConstants.PARAM_TASK_OF_COURSE_ID
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.frames.entity.TaskDetail
import com.sugar.library.util.CommonUtils
import kotlinx.android.synthetic.main.activity_after_class_task.*

/**
 * Created by Wangsw on 2020/9/27 0027 13:43.
 * </br>
 *  课后任务详情
 */
class TaskDetailActivity : BaseMVPActivity<TaskDetailPresenter>(), TaskDetailView {

    private var downLoadUrl = ""

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun getLayoutId() = R.layout.activity_after_class_task

    override fun initViews() {
        setMainTitle("课后任务详情")
        if (intent.extras == null || !intent.extras!!.containsKey(PARAM_TASK_OF_COURSE_ID)) {
            finish()
            return
        }
        val courseId = intent.extras!!.getString(PARAM_TASK_OF_COURSE_ID)
        presenter.loadDetail(this, courseId)
    }

    override fun bindDetail(it: TaskDetail) {
        task_name_tv.text = it.native_name
        remaining_tv.text = getString(R.string.remaining_appointments,it.native_num)

    }

    @OnClick(
        R.id.download_tv,
        R.id.upload_tv
    )
    fun onClick(view: View) {
        if (CommonUtils.isDoubleClick(view)) {
            return
        }
        when (view.id) {
            R.id.download_tv -> {

            }
            R.id.upload_tv -> {

            }
            else -> {
            }
        }
    }


}