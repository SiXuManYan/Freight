package com.fatcloud.account.ui.task.lists

import com.fatcloud.account.ui.task.lists.TaskContainerActivity
import com.fatcloud.account.ui.task.lists.TaskContainerView
import dagger.Module
import dagger.Provides

/**
 * Created by Wangsw on 2020/11/12 0012 15:09.
 * </br>
 *
 */
@Module
class TaskContainerModule {

    @Provides
    fun viewProvider(activity: TaskContainerActivity): TaskContainerView {
        return activity
    }


}