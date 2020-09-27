package com.ftacloud.student.ui.task

import com.ftacloud.student.ui.task.detail.TaskDetailActivity
import com.ftacloud.student.ui.task.detail.TaskDetailView
import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/27 0027 14:23.
 * </br>
 *
 */
@Module
class TaskModule {

    @Provides
    fun viewProvider(activity: TaskActivity): TaskView {
        return activity
    }


}