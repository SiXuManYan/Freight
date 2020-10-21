package com.ftacloud.student.ui.task.detail

import com.ftacloud.student.frames.entity.TaskDetail
import com.sugar.library.frames.network.response.BaseTaskView

/**
 * Created by Wangsw on 2020/9/27 0027 13:42.
 * </br>
 *
 */
interface TaskDetailView :BaseTaskView{
    abstract fun bindDetail(it: TaskDetail)

}