package com.fatcloud.account.ui.task.detail

import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/9/27 0027 13:43.
 * </br>
 *
 */
@Module
class TaskDetailModule  {

    @Provides
    fun viewProvider(activity: TaskDetailActivity): TaskDetailView {
        return activity
    }
}