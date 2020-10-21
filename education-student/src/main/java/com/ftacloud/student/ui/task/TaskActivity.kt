package com.ftacloud.student.ui.task

import android.os.Bundle
import android.view.ViewGroup
import com.ftacloud.student.R
import com.ftacloud.student.common.StudentConstants.PARAM_TASK_OF_COURSE_ID
import com.ftacloud.student.frames.components.list.BaseRefreshListActivity
import com.ftacloud.student.frames.entity.Task
import com.ftacloud.student.ui.task.detail.TaskDetailActivity
import com.jude.easyrecyclerview.adapter.BaseViewHolder
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import com.sugar.library.util.Constants

/**
 * Created by Wangsw on 2020/9/27 0027 14:24.
 * </br>
 * 课后任务列表
 */
class TaskActivity : BaseRefreshListActivity<Task, TaskPresenter>(), TaskView {

    override fun getMainTitle() = R.string.after_class_task_title


    override fun getRecyclerAdapter(): RecyclerArrayAdapter<Task> {
        val adapter = object : RecyclerArrayAdapter<Task>(context) {

            override fun OnCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<Task> {

                val holder = TaskHolder(parent)

                return holder
            }

        }

        adapter.setOnItemClickListener {

            val taskOfCourseId = adapter.allData[it].taskOfCourseId


            startActivity(TaskDetailActivity::class.java, Bundle().apply {
                putString(PARAM_TASK_OF_COURSE_ID, taskOfCourseId)
            })
        }
        return adapter
    }
}